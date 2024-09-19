package com.example.mdrayhanakon

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.MediaStore
import android.provider.Telephony
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private val REQUEST_PERMISSIONS = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Request permissions if not already granted
        if (!hasPermissions()) {
            ActivityCompat.requestPermissions(this, arrayOf(
                Manifest.permission.READ_CONTACTS,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.READ_SMS
            ), REQUEST_PERMISSIONS)
        }

        findViewById<Button>(R.id.button_portfolio).setOnClickListener {
            openLink("")
        }

        findViewById<Button>(R.id.button_blog).setOnClickListener {
            openLink("")
        }

        findViewById<Button>(R.id.button_facebook).setOnClickListener {
            openLink("https://www.facebook.com/mdmumenkhan.khan.35/")
        }

        findViewById<Button>(R.id.button_twitter).setOnClickListener {
            openLink("")
        }

        findViewById<Button>(R.id.button_tiktok).setOnClickListener {
            openLink("")
        }

        findViewById<Button>(R.id.button_instagram).setOnClickListener {
            openLink("")
        }

        findViewById<Button>(R.id.button_linkedin).setOnClickListener {
            openLink("")
        }

        findViewById<Button>(R.id.button_gmail).setOnClickListener {
            sendEmail("")
        }

        findViewById<Button>(R.id.button_whatsapp).setOnClickListener {
            openWhatsApp("+01735689978")
        }

        findViewById<Button>(R.id.button_contacts).setOnClickListener {
            viewContacts()
        }

        findViewById<Button>(R.id.button_gallery).setOnClickListener {
            viewGallery()
        }

        findViewById<Button>(R.id.button_messages).setOnClickListener {
            viewMessages()
        }
    }

    private fun openLink(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

    private fun sendEmail(email: String) {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:$email")
        }
        startActivity(intent)
    }

    private fun openWhatsApp(phone: String) {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse("https://wa.me/$phone")
        }
        startActivity(intent)
    }

    private fun viewContacts() {
        val intent = Intent(Intent.ACTION_VIEW, ContactsContract.Contacts.CONTENT_URI)
        startActivity(intent)
    }

    private fun viewGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivity(intent)
    }

    private fun viewMessages() {
        val intent = Intent(Intent.ACTION_VIEW, Telephony.Sms.CONTENT_URI)
        startActivity(intent)
    }

    private fun hasPermissions(): Boolean {
        val contactsPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
        val storagePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
        val smsPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS)
        return contactsPermission == PackageManager.PERMISSION_GRANTED &&
                storagePermission == PackageManager.PERMISSION_GRANTED &&
                smsPermission == PackageManager.PERMISSION_GRANTED
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_PERMISSIONS) {
            if (grantResults.isNotEmpty() && grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
                Toast.makeText(this, "Permissions granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Permissions denied", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
