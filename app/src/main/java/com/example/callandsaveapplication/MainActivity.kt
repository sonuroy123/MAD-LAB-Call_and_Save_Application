package com.example.callandsaveapplication

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.ActivityCompat
import java.util.jar.Manifest
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun num(v: View?){
        var b = v as Button
        var txt = b.text.toString()
        var e=findViewById<EditText>(R.id.editNum)
        e.setText(e.text.toString()+txt)
    }
    fun call(v:View?){
        var e=findViewById<EditText>(R.id.editNum)
        var pnum = e.text.toString()
        var dialIntent = Intent(Intent.ACTION_CALL)
        dialIntent.data = Uri.parse("tel:+91"+pnum)
        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CALL_PHONE),0)
        startActivity(dialIntent)
    }
    fun save(v:View?){
        val ename = findViewById<EditText>(R.id.editName)
        val e = findViewById<EditText>(R.id.editNum)
        val intent = Intent(ContactsContract.Intents.Insert.ACTION)
        intent.setType(ContactsContract.RawContacts.CONTENT_TYPE)
        intent.putExtra(ContactsContract.Intents.Insert.NAME, ename.text.toString())
        intent.putExtra(ContactsContract.Intents.Insert.PHONE, e.text.toString())
        startActivityForResult(intent, 1)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)
        if(requestCode == 1){
            if(resultCode == Activity.RESULT_OK){
                Toast.makeText(this, "Contacts Added", Toast.LENGTH_SHORT).show()
            }
            if(resultCode == Activity.RESULT_CANCELED){
                Toast.makeText(this, " Cancelled Contacts Added", Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun del(v: View?){
        var e=findViewById<EditText>(R.id.editNum)
        e.setText(" ")
    }
}