package com.lnd.RencontreAfricaine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.FirebaseDatabase

class ReportBugActivity : AppCompatActivity() {
    companion object{
        var title = ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report_bug)

        findViewById<TextInputEditText>(R.id.edTitle)
            .setText(title)

        //btn to quit
        findViewById<ImageView>(R.id.btnBackReportBug)
            .setOnClickListener { finish() }

        val btnSend = findViewById<AppCompatButton>(R.id.btnSendReportBug)
        btnSend.setOnClickListener {
            val title = findViewById<TextInputEditText>(R.id.edTitle).text.toString()
            val description = findViewById<TextInputEditText>(R.id.edDescription).text.toString()
            if(title.isEmpty()  || title == "null"){
                showError("Vous devez mettre un titre a votre rapport de bug")
                return@setOnClickListener
            }
            if(description.isEmpty()  || description == "null"){
                showError("Vous devez mettre une description a votre rapport de bug")
                return@setOnClickListener
            }

            val newMap: MutableMap<String, Any> = HashMap()
            newMap["userId"] = ""
            newMap["title"] = title
            newMap["description"] = description

            if (MainActivity.currentUser!=null){
                newMap["userId"] = MainActivity.currentUser!!.userData.id
            } else if(MainActivity.newUserData !=null){
                newMap["userId"] = MainActivity.newUserData!!["id"].toString()
            }


            val fb = FirebaseDatabase.getInstance().reference.child("Others").child("Report")
            val id = fb.push().key!!
            fb.child(id).updateChildren(newMap)
                .addOnSuccessListener {
                    findViewById<TextInputEditText>(R.id.edTitle).setText("")
                    findViewById<TextInputEditText>(R.id.edDescription).setText("")
                    AlertDialog.Builder(this)
                        .setTitle("Message envoyé")
                        .setMessage("Votre message a bien été envoyé !")
                        .setPositiveButton("Ok"){_,_ -> }
                        .create().show()

                }
                .addOnFailureListener {
                    showError(it.toString())
                }
        }
    }

    private fun showError(e: String) {
        AlertDialog.Builder(this)
            .setTitle("Erreur")
            .setMessage("Une erreur est survenue: \n$e")
            .setPositiveButton("Ok"){_,_ -> }
            .create().show()
    }

    override fun onPause() {
        title =""
        super.onPause()
    }
}