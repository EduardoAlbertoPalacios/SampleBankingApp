package com.example.storidemoapp.ui.component.AlertDialog


data class AlertDialogModel(
    val dialogType: AlertDialogType,
    val title: String,
    val body: String,
    val actionButton: () -> Unit
)