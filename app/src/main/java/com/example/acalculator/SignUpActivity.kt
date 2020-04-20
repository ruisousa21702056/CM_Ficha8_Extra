package com.example.acalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.apache.commons.codec.digest.DigestUtils

const val EXTRA_USERS_SIGNUP = "com.example.intent.EXTRA_USERS_SIGNUP"

class SignUpActivity : AppCompatActivity() {

    var users = ArrayList<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        if(intent.getParcelableArrayListExtra<User>(EXTRA_USERS_SIGNUP) != null){
            users = intent.getParcelableArrayListExtra<User>(EXTRA_USERS_SIGNUP)
        }

        submit_regist_button.setOnClickListener {
            var name = findViewById<EditText>(R.id.input_regist_name)
            var email = findViewById<EditText>(R.id.input_regist_email)
            var pass = findViewById<EditText>(R.id.input_regist_password)
            var confirm_pass = findViewById<EditText>(R.id.input_regist_confirmpassword)

            val text_name = name.text.toString()
            val text_email = email.text.toString()
            val text_pass = pass.text.toString()
            val text_confirm_pass = confirm_pass.text.toString()

            if(!text_name.equals("") && !text_email.equals("") && text_pass.equals(text_confirm_pass)){
                val text_pass_aux = DigestUtils.sha256Hex(text_pass)
                val user = User(text_name,text_email, text_pass_aux)
                users.add(user)
                Toast.makeText(this,"Registo efetuado com sucesso",Toast.LENGTH_LONG).show()
                intent.apply { putParcelableArrayListExtra(EXTRA_USERS_LOGIN,ArrayList(users)) }
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
            else {
                Toast.makeText(this,"As passwords devem ser iguais",Toast.LENGTH_LONG).show()
            }
        }

        back_regist_button.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}
