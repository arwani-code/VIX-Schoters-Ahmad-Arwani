package com.arwani.ahmad.schotersnews.util

import org.junit.Assert
import org.junit.Test

class DateFormatterTest {
    @Test
    fun `given correct ISO 8601 format`() {
        val currentDate = "2023-01-04T13:25:00Z"
        Assert.assertEquals("04 Jan 2023 | 20:25", DateFormatter.formatDate(currentDate))
    }
}