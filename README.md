#SDE Challenge Solution
by Steve Kotsopoulos

## Design Solution

My solution to the design question is provided in `design/WebsiteAnalytics.docx` and `design/WebsiteAnalytics.pdf` - both documents have the same content.

## Coding Solution

All other files are for the coding question.

In order to easily support new requirements with minimal coding, I wanted to base my data structure on the Collections API. I wanted my solution to be performant for inserts and access. We needed to preserve ordering, so we needed a solution that implements the List interface. I considered both LinkedList and ArrayList. Vector didn't make sense because it is synchronized, so it would be slower.

In order to support high-precision arithmetic, elements are stored in BigDecimal.

I created 2 implementations, the best implementation is based on ArrayList, which is O(1) on average for inserts and O(1) on average for access and backed by a dynamically resizing array.

The other implementation was based on LinkedList, which is O(1) on inserts and O(n) on access and backed by a doubly-linked list.

### Assumptions
In a given business context, it is normal to have a commonly-used form of moving average. For example, when analyzing stock prices you might be interested in the 5-day moving average. Thus, I decided to make the `sampleSize` a constructor parameter rather than a parameter on the `getMovingAverage()` function.

### Navigating the Source Code
1. Import the project into your favourite IDE via 'import existing maven project'
2. Generate javadoc by running `mvn javadoc:javadoc`
3. View the generated javadoc under `target/site/apidocs/index.html`
4. Run the unit tests found in `src/test/java/com/paytm/sdechallenge`

### Performance

Note that each of the 2 test classes have a `testMovingAverageScalability` which was used to compare performance of the ArrayList and LinkedList implementations when dealing with large lists. On a Macbook Pro with a 2.5 GHz Core i7 processor, the ArrayList test took 6 seconds, while the LinkedList test took 10 seconds.

The test duration includes both the time to insert elements into the list, as well as calculate the moving average. We insert 9 million elements, and calculate the moving average over the most recent 6 million.

If we only consider the time to calculate the moving average, and ignore the time to insert elements, then both implementations are almost the same, taking about 590 ms on the same hardware.

On the assumption that we might request the moving average a large number of times without doing inserts into the list, a trivial performance enhancement was implemented to cache the most recent result.

### Future Enhancements

Depending on the access pattern of how this interface is used in production, an area to explore would be to re-calculate the moving average incrementally as each element is added.


