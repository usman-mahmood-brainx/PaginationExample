package com.example.paginationexample.models

data class Passengers(
    val data: List<Data>,
    val totalPages: Int,
    val totalPassengers: Int
)