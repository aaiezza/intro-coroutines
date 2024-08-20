package tasks

import contributors.User

/*
 In the initial list each user is present several times, once for each
 repository he or she contributed to.
 Merge duplications: each user should be present only once in the resulting list
 with the total value of contributions for all the repositories.
 Users should be sorted in descending order by their contributions.

 The corresponding test can be found in test/tasks/AggregationKtTest.kt.
 You can use 'Navigate | Test' menu action (note the shortcut) to navigate to the test.
*/
fun List<User>.aggregate(): List<User> =
    groupingBy(User::login)
        .fold(0) { c, user -> c + user.contributions }
        .mapAndSortDescendingByContributions()

fun List<User>.aggregateWithGroupBy(): List<User> =
    groupBy(User::login, User::contributions)
        .mapValues { (_, contributions) -> contributions.sum() }
        .mapAndSortDescendingByContributions()

fun List<User>.aggregateWithTutorialSolution() =
    groupBy { it.login }
        .map { (login, group) -> User(login, group.sumOf { it.contributions }) }
        .sortedByDescending { it.contributions }

fun Map<String, Int>.mapAndSortDescendingByContributions(): List<User> =
    map { (login, contributions) -> User(login, contributions) }
        .sortedByDescending { it.contributions }
