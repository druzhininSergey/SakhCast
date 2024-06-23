package com.example.sakhcast.data.downloader

interface Downloader {
    fun downloadFile(url: String, fileName: String): Long
}