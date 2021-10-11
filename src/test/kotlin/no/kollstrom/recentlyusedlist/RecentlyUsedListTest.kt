package no.kollstrom.recentlyusedlist

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

internal class RecentlyUsedListTest {

    @Test
    internal fun `most recently added at front`() {
        val recentlyUsedList = RecentlyUsedList(maximum = 3)
        recentlyUsedList.add("first")
        recentlyUsedList.add("second")
        val expected = "most recent"
        recentlyUsedList.add(expected)
        assertEquals(expected, recentlyUsedList.mostRecent())
    }

    @Test
    internal fun `adding more than max deletes oldest entry`() {
        val recentlyUsedList = RecentlyUsedList(maximum = 2)
        recentlyUsedList.add("first")
        val expected = "second"
        recentlyUsedList.add(expected)
        recentlyUsedList.add("third")
        assertEquals(expected, recentlyUsedList.leastRecent())
    }

    @Test
    internal fun `not allowed to add more than maximum number of element`() {
        val expected = 2
        val recentlyUsedList = RecentlyUsedList(maximum = expected)
        recentlyUsedList.add("first")
        recentlyUsedList.add("second")
        recentlyUsedList.add("third")
        assertEquals(expected, recentlyUsedList.count())
    }

    @Test
    internal fun `does not contain duplicates`() {
        val recentlyUsedList = RecentlyUsedList(maximum = 2)
        recentlyUsedList.add("a")
        recentlyUsedList.add("a")
        assertEquals(expected = 1, recentlyUsedList.count())
    }

    @Test
    internal fun `duplicates are added to front of list`() {
        val recentlyUsedList = RecentlyUsedList(4)
        val abra = "abra"
        val kadabra = "kadabra"
        recentlyUsedList.add(abra)
        recentlyUsedList.add(kadabra)
        recentlyUsedList.add("simsala")
        recentlyUsedList.add(abra)
        assertEquals(abra, recentlyUsedList.mostRecent())
        assertEquals(kadabra, recentlyUsedList.leastRecent())
    }

    @Test
    internal fun `empty list throws appropriate exception`() {
        val recentlyUsedList = RecentlyUsedList(maximum = 2)
        assertThrows<NoSuchElementException> {
            recentlyUsedList.mostRecent()
        }
    }

    @Test
    internal fun `maximum must be positive`() {
        assertThrows<IllegalArgumentException> {
            RecentlyUsedList(maximum = -1)
        }
    }

    @Test
    internal fun `maximum must be bigger than 0`() {
        assertThrows<IllegalArgumentException> {
            RecentlyUsedList(maximum = 0)
        }
    }
}
