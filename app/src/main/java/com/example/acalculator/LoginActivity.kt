package com.example.acalculator

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import org.apache.commons.codec.digest.DigestUtils

class LoginActivity : AppCompatActivity() {

    var users = ArrayList<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val pass_aux = DigestUtils.sha256Hex("pass")
        var user = User("Rui","mail",pass_aux)
        users.add(user)

        regist_login_button.setOnClickListener {
            val intent: Intent = Intent(this,  SignUpActivity::class.java)
            intent.apply {putParcelableArrayListExtra("users_from_login", ArrayList(users))}
            startActivity(intent)
            finish()
        }

        login_button.setOnClickListener {
            var email = findViewById<EditText>(R.id.input_login_email)
            var pass = findViewById<EditText>(R.id.input_login_password)

            val text_email = email.text.toString()
            val text_pass = pass.text.toString()

            val text_pass_aux = DigestUtils.sha256Hex(text_pass)
            if(!users.isNullOrEmpty() && !text_email.equals("") && !text_pass.equals("")){
                var have_user = false
                for(user in users) {
                    if(user.email == text_email && user.password == text_pass_aux){
                        have_user = true
                        var intentLogin = Intent(this,MainActivity::class.java)
                        intentLogin.apply { putExtra("user_name", user.name) }
                        intentLogin.apply { putExtra("user_email", user.email) }
                        startActivity(intentLogin)
                        finish()
                    }
                    if(!have_user){
                        Toast.makeText(this,"E-mail/Password inválidos", Toast.LENGTH_LONG).show()
                    }
                }
            }
            else{
                Toast.makeText(this,"E-mail/Password inválidos", Toast.LENGTH_LONG).show()
            }
        }
    }
}
