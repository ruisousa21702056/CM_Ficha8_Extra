package com.example.acalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import org.apache.commons.codec.digest.DigestUtils

const val EXTRA_USERS_LOGIN = "com.example.intent.EXTRA_USERS_LOGIN"

class LoginActivity : AppCompatActivity() {

    var users = ArrayList<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        if(intent.getParcelableArrayListExtra<User>(EXTRA_USERS_LOGIN) != null){//Não entra aqui
            users = intent.getParcelableArrayListExtra<User>(EXTRA_USERS_LOGIN)
        }

        regist_login_button.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
            if(!users.isNullOrEmpty()){
                intent.apply { putParcelableArrayListExtra(EXTRA_USERS_SIGNUP,ArrayList(users)) }
            }
            finish()
        }

        login_button.setOnClickListener {
            var email = findViewById<EditText>(R.id.input_login_email)
            var pass = findViewById<EditText>(R.id.input_login_password)

            val text_email = email.text.toString()
            val text_pass = pass.text.toString()

            val text_pass_aux = DigestUtils.sha256Hex(text_pass)
            if(!users.isNullOrEmpty() && !text_email.equals("") && !text_pass.equals("")){
                for(user in users) {
                    if(user.email == text_email && user.password == text_pass_aux){
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    }
                }
                Toast.makeText(this,"E-mail/Password inválidos", Toast.LENGTH_LONG).show()
            }
            Toast.makeText(this,"E-mail/Password inválidos", Toast.LENGTH_LONG).show()
        }
    }
}
