6.2 f) The project has passed the defined quality gate, although it still has no security reviewing whatsoever (but only has one hotspot for possible problems), it only has one bug and some code smells... which makes the program of easy maintainability and with reasonable reliability.

6.2 g)
| Issue | Problem description  | How to solve  |
| Bug  | Save and re-use this "Random" | Store the random created |
| Security Reviews  | Make sure that using this pseudorandom number generator is safe here.  |   |---| 
Code smells (Major):
-Refactor the code in order to not assign to this loop counter from within the loop body.
-Replace this use of System.out or System.err by a logger.
