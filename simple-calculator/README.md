## Sample app to apply git

###1. Pulling commits from the middle of git history.

Reference:

- https://stackoverflow.com/questions/31462683/git-pull-till-a-particular-commit
- https://devopscube.com/checkout-clone-specific-git-commit-id-sha/

####1.1 Checkout to new branch

Check log by using `git log`

Checkout new branch, start from

```
git checkout -b "enhancement-features" 7fe0758084dcd66bc3ea27cc9e4a686f736216ce
```

####1.2 Add these features to your calculator sequentially in different commits:

#####1.2.1 Support large numbers in math operations (use BigInteger instead of Integer/int type).

Change to use `BigInteger`

```
    public static BigInteger sum(Integer a, Integer b) {
        BiFunction<Integer, Integer, BigInteger> func = (x, y) -> BigInteger.valueOf(x + y);
        BigInteger result = func.apply(a, b);
        return result;
    }
```

#####1.2.2 Add Intermediate logging of math operation results.

Add logs library to maven, add logs to code

```
logger.info("Result = {}", result);
```

and apply unit test

```
    @Test
    public void givenTwoNumber_whenSumIsCalled_thenReturnResult() {
        assertEquals(BigInteger.valueOf(5), Calculator.sum(2, 3));
    }
```

####1.3 Using cherry -pick

Now let’s imagine that customer wants changes regarding allowing large number (BigInteger support) in nearest patch. Try to pull these changes to the main branch of this task “task${number}” using git cherry-pick and git rebase commands.

Reference: https://stackoverflow.com/questions/2474353/how-to-copy-commits-from-one-branch-to-another

in branch `enhancement-features` try `git log` and copy the SHA `941631d8f5940f75ad0557e9200c75e425f81563`

switch to branch dev `git checkout dev` 

use cherry-pick commit 
```
git cherry-pick 941631d8f5940f75ad0557e9200c75e425f81563`
```

###2. Investigate changes in files in GIT repository by git blame command.

Reference: https://www.atlassian.com/git/tutorials/inspecting-a-repository/git-blame

Use 
```
git blame simple-calculator/src/main/java/com/tuanmhoang/calculator/Calculator.java
```
