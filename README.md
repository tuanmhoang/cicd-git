# cicd-git

## Jenkins

### 1. Setup Jenkins locally.

Reference: 
- https://www.cloudbees.com/blog/how-to-install-and-run-jenkins-with-docker-compose
- https://dev.to/andresfmoya/install-jenkins-using-docker-compose-4cab

To start Jenkins, run `docker compose up -d`

To check, try `docker compose ps`

Then access to http://localhost:8081 

To check the password, try `docker exec <container_name> cat /var/jenkins_home/secrets/initialAdminPassword`. Reference: https://stackoverflow.com/questions/51007343/how-can-i-find-initial-password-for-jenkins/52399699 

Jenkins is installing

![image](https://user-images.githubusercontent.com/37680968/153584176-55602d77-4e0f-46d8-a66d-1aa672794b9f.png)


### 2. Project to use

Using this repo: https://github.com/tuanmhoang/groovy-build-tools

### 3. Use names for job described in brackets (name: $nameOfJob).

In pipeline script, add to `parameters`

```
    parameters {        
        string(name: 'nameOfJob', defaultValue: 'Builder Application', description: 'Name of the job. Default is BuilderApp')
    }
```

![image](https://user-images.githubusercontent.com/37680968/153748251-ca5f2887-47f4-469c-9aaa-c0c694d1436c.png)

To config Maven, follow

![image](https://user-images.githubusercontent.com/37680968/154002276-98476bfd-8e0d-4e15-9933-a09bc4a09080.png)


### 4. Create build job

Build and get the result

![image](https://user-images.githubusercontent.com/37680968/153748378-961dde26-3bac-4782-8036-c856966bad40.png)

#### 4.1 Should build when SCM has changes.

Reference: https://www.youtube.com/watch?v=Z3S2gMBUkBo

**1. Connect Jenkins to github**

**1.1 Get tokens from github**

Github > Settings > Developer settings > Personal access token > Generate token with `repo`, `admin repo hook` and `notification`

**1.2 Add token to Jenkins**

Jenkins Main page > Manage Jenkins > Configuration System > Github server > Kind: secret text > Verify by using `Test connection`

**1.3 Download github intergration plugin**

Manage Jenkins > Install plugins

**1.4 Configure on github**

Go to github > Settings > Web hook

Go to sub-folder https://stackoverflow.com/questions/52372589/jenkins-pipeline-how-to-change-to-another-folder

#### 4.2 Job should be parameterized with maven or gradle (user should be able to choose it in job parameter).

Can use `Parameterized Trigger` plugin or use pipeline script

Do these steps:

**1. Define Gradle in Jenkins**

Go to Manage Jenkins > Global Tool Configuration and define Gradle there

**2. Define Gradle in pipeline script**

In pipeline script, add Gradle `gradle 'G7'`

**3. Add choice to select build tool**

In pipeline script, add to `parameters`

```
    parameters {
        choice(name: 'selectedBuildTool', choices: ['Maven', 'Gradle'], description: 'Select build tool')       
    }
```

#### 4.3 Use promotion plugin. Should be available two promo stars.

Go to Manage Jenkins > Plugin and install `promoted builds` plugin

##### 4.3.1 First stars should be applied automatically to successful build (QA).

![image](https://user-images.githubusercontent.com/37680968/154251881-9b14e437-e2b0-4847-b89f-74d36e229762.png)

![image](https://user-images.githubusercontent.com/37680968/154252130-d44af33e-c016-4b57-bbe3-f6340172686d.png)

##### 4.3.2 Second stars should be applied manually (Prod).

![image](https://user-images.githubusercontent.com/37680968/154252048-b1b14556-7f55-4287-8acf-9a835ba02e5b.png)

![image](https://user-images.githubusercontent.com/37680968/154252236-cd86b1b9-e68c-429e-8468-894f7831cb18.png)

### 5. Create continuous Job (name: continuous).

#### 5.1 Should build every night at 2:00 AM.

Reference: https://stackoverflow.com/questions/7000251/how-to-schedule-a-build-in-jenkins

Can try `00 02 * * 0-6`

![image](https://user-images.githubusercontent.com/37680968/154253540-87de3cc6-7fba-42a6-a7b3-da3448ac4b46.png)

### 6. Create Deploy Job (name: deploy).

#### 6.1 Should be parameterized with artifacts prompted with QA star.

![image](https://user-images.githubusercontent.com/37680968/154397803-abd4f41a-3d1b-46b8-b675-19e44ac039e2.png)

#### 6.2 Use Container Deploy plugin.

Reference: https://www.youtube.com/watch?v=g7khm3IAuzw

Sharing same workspace: https://stackoverflow.com/questions/21520475/same-workspace-for-multiple-jobs

### 7. Setup machine on Amazon.

#### 7.1 Setup additional users for Jenkins. 

#### 7.2 Setup tomcat.

Notice that:

- need to allow port `8080` or any ports that is using tomcat

- need to allow access to manager page: https://stackoverflow.com/questions/41675813/the-username-you-provided-is-not-allowed-to-use-the-text-based-tomcat-manager-e

#### 7.3 Setup cloud machine as a slave on Jenkins.

### 8. Create deploy job (name: deploy_cloud).

![image](https://user-images.githubusercontent.com/37680968/154468457-dd55fdeb-1f3a-4301-b7bf-0dce2dff7717.png)

![image](https://user-images.githubusercontent.com/37680968/154468616-cfc0cb80-d83a-4002-89dd-190e76809aab.png)

#### 8.1 Should deploy on cloud machine.

![image](https://user-images.githubusercontent.com/37680968/154467454-3739cdcc-60dc-4f43-8bcd-a64d1fc06ae2.png)

![image](https://user-images.githubusercontent.com/37680968/154468340-506e2e55-867b-407b-a2f6-5189bc1ce8bc.png)

#### 8.2 Should be parameterized with artifacts promoted with Prod star.

![image](https://user-images.githubusercontent.com/37680968/154471761-d84a653f-24aa-4dd6-bc25-91fc648315e6.png)

![image](https://user-images.githubusercontent.com/37680968/154471638-169dcea8-71c6-45d5-9b6a-dc5db931490c.png)

#### 8.3 Use Container Deploy plugin.

### 9. Create PipeLine (delivery_pipeline).

#### 9.1. Job should be parameterized with maven or gradle (user should be able to choose it in job parameter).

#### 9.2. Step Build according to parameter (release).

#### 9.3. Step Deploy on test (deploy job).

#### 9.4. Check using curl that deployment is successful (https://curl.haxx.se/download.html) if deployment is successful deploy on cloud (deploy_cloud).

Create token on Jenkins

![image](https://user-images.githubusercontent.com/37680968/154472234-f4bcada7-11f0-4585-b5fc-81ac7d73359c.png)

Sample response


```
{"_class":"hudson.model.FreeStyleBuild","actions":[{"_class":"hudson.model.ParametersAction","parameters":[{"_class":"hudson.plugins.promoted_builds.parameters.PromotedBuildParameterValue","name":"Accepted to Prod","jobName":"prodJob","number":"45"}]},{"_class":"hudson.model.CauseAction","causes":[{"_class":"hudson.model.Cause$UserIdCause","shortDescription":"Started by user Tuan","userId":"tuamhoang","userName":"Tuan"}]},{},{"_class":"org.jenkinsci.plugins.displayurlapi.actions.RunDisplayAction"}],"artifacts":[],"building":false,"description":null,"displayName":"#1","duration":6864,"estimatedDuration":6864,"executor":null,"fullDisplayName":"deploy_cloud #1","id":"1","keepLog":false,"number":1,"queueId":123,"result":"SUCCESS","timestamp":1645096909329,"url":"http://localhost:8081/jenkins/job/deploy_cloud/1/","builtOn":"","changeSet":{"_class":"hudson.scm.EmptyChangeLogSet","items":[],"kind":null},"culprits":[]}* Connection #0 to host localhost left intact
```

## Pipeline flow

## Problem and solution

### Tomcat incorrect username password

**Problem**

```
Caused by: java.io.IOException: Server returned HTTP response code: 401 for URL: http://localhost:8080/manager/text/list
	at java.base/sun.net.www.protocol.http.HttpURLConnection.getInputStream0(HttpURLConnection.java:1924)
	at java.base/sun.net.www.protocol.http.HttpURLConnection.getInputStream(HttpURLConnection.java:1520)
	at org.codehaus.cargo.container.tomcat.internal.TomcatManager.invoke(TomcatManager.java:577)
	... 22 more
org.codehaus.cargo.container.tomcat.internal.TomcatManagerException: The username and password you provided are not correct (error 401)
	at org.codehaus.cargo.container.tomcat.internal.TomcatManager.invoke(TomcatManager.java:704)
	at org.codehaus.cargo.container.tomcat.internal.TomcatManager.list(TomcatManager.java:882)
	at org.codehaus.cargo.container.tomcat.internal.TomcatManager.getStatus(TomcatManager.java:895)
	at 
....
```    

**Solution**

https://stackoverflow.com/questions/41675813/the-username-you-provided-is-not-allowed-to-use-the-text-based-tomcat-manager-e


**Problem**

```
Caused by: org.codehaus.cargo.container.tomcat.internal.TomcatManagerException: The username you provided is not allowed to use the text-based Tomcat Manager (error 403)
	at org.codehaus.cargo.container.tomcat.internal.TomcatManager.invoke(TomcatManager.java:710)
	at org.codehaus.cargo.container.tomcat.internal.TomcatManager.list(TomcatManager.java:882)
	at org.codehaus.cargo.container.tomcat.internal.TomcatManager.getStatus(TomcatManager.java:895)
	at org.codehaus.cargo.container.tomcat.internal.AbstractTomcatManagerDeployer.redeploy(AbstractTomcatManagerDeployer.java:161)
```

**Solution**

Add role `manager-script`

**Problem**

Using `curl  -s --user <username>:<token> http://localhost:8081/job/deploy_cloud/lastBuild/api/json`

HTTP ERROR 401 Unauthorized

***Solution**

Some discussion:

https://github.com/jenkinsci/oic-auth-plugin/issues/25

https://github.com/jenkinsci/oic-auth-plugin/issues/41

Check for the username and password if it is correct