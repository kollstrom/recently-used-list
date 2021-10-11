package no.kollstrom.recentlyusedlist

import kotlin.collections.ArrayDeque

class RecentlyUsedList(private val maximum: Int) {
    private val stack: ArrayDeque<String> = ArrayDeque(initialCapacity = maximum)

    init {
        if (maximum <= 0) throw IllegalArgumentException("Maximum must be positive and bigger than 0")
    }

    fun add(element: String) {
        if (stack.count() == maximum) {
            stack.removeLast()
        } else if (stack.contains(element)) {
            stack.remove(element)
        }
        stack.addFirst(element)
    }

    fun mostRecent(): String = stack.first()

    fun leastRecent(): String = stack.last()

    fun count(): Int = stack.count()
}
