package com.example.simpleapp

/*import com.google.api.client.extensions.android.http.AndroidHttp
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential
import com.google.api.client.json.gson.GsonFactory
import com.google.api.services.sheets.v4.Sheets
import com.google.api.services.sheets.v4.model.*


 */
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.simpleapp.ui.theme.SimpleAppTheme
import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.layout.FlowRowScopeInstance.align
//import androidx.compose.foundation.layout.FlowRowScopeInstance.alignByBaseline
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.core.content.ContextCompat.startActivity
import kotlinx.coroutines.delay

import java.io.InputStream

class MainActivity : ComponentActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        setContent {
            SimpleAppTheme {
                   Surface (
                       modifier = Modifier.fillMaxSize(),
                       color = MaterialTheme.colorScheme.background
                   ){
                       BackGroundImage()
                   }


            }
        }
    }
}


@Composable
fun LoginScreen(modifier: Modifier = Modifier) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var userNameValid by remember { mutableStateOf(false) }
    var passwordValid by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth()


        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        val context = LocalContext.current

        if (userNameValid && passwordValid) {
            Text("Valid")
            LaunchedEffect(Unit) {
                delay(1000)
                val intent = Intent(context, HomePage::class.java)
                context.startActivity(intent)
            }
        }
        Button(
            onClick = {


                if (password.isNotEmpty() && username.isNotEmpty()) {
                    passwordValid = true
                    userNameValid = true
                }


            },
            modifier = Modifier.fillMaxWidth()


        )


        {


            Text(
                text = "Login",
                color = Color.Yellow
            )
        }



        Button(
            onClick = {
                //a new page to reset password

                /*  val intent  = Intent(context, SignUp::class.java)
                context.startActivity(intent)
                */

            },

            colors = ButtonDefaults.buttonColors(
                containerColor = Color.DarkGray,
                contentColor = Color.Green

            ),

            modifier = Modifier
                .height(30.dp)
                .width(170.dp)
        )

        {
            Text(
                text = "Forgot Password?",
                style = TextStyle(fontSize = 11.sp)




            )
        }
    }

        Column(
        modifier = modifier
            .fillMaxWidth()
            .offset(x = 200.dp, y = 88.dp),
            verticalArrangement = Arrangement.Center,
    )

        {
            val context = LocalContext.current
        Button(
            onClick = {
                val intent = Intent(context, CreateAccount::class.java)
                context.startActivity(intent)

            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.DarkGray,
                contentColor = Color.Green

            ),
            modifier = Modifier
                .height(30.dp)
                .width(170.dp)

        )
        {
            Text(
                text = "Create Account",
                style = TextStyle(fontSize = 11.sp)
            )
        }
    }

    Greeting(name = username)
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {

    Column(
       modifier = modifier
           .fillMaxWidth()
           .padding(top = 275.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(
            text = "Welcome $name!",
            style = TextStyle(fontSize = 50.sp),
            color = Color.Red


        )
    }
}




@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SimpleAppTheme {

        LoginScreen()
    }
}






@Composable
fun BackGroundImage(){
    Box(modifier = Modifier.fillMaxSize()){
        Image(
            painter = painterResource(id = R.drawable.weights),
            contentDescription = "Background",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize()
        )
    }

    LoginScreen()

}

/*create class to basically help create an account

1. User can pick out an user name (also email? and check that the username isn't already in use
2. User can pick out a password
3. Store that data on a google spread sheet

 */

class CreateAccount : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContent {
            SimpleAppTheme {
                CreateAccountScreen()

    }
}
    }

    @Composable
    fun CreateAccountScreen(modifier: Modifier = Modifier) {

        Column (
            modifier = Modifier
                .height(64.dp)
                .offset(x = 0.dp, y = 255.dp)
                .fillMaxSize(),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
        )
        {
            Text(
                text = "Create Account",
                style = TextStyle(fontSize = 50.sp),

                color = Color.Gray

            )

            Spacer(modifier = Modifier.height(8.dp))
        }



        //creating variables for where the data is captured
        var userName by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }

        var userNameValid by remember { mutableStateOf(false) }
        var passwordValid by remember { mutableStateOf(false) }
        var emailValid by remember { mutableStateOf(false) }

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
        ){
            
            Spacer(modifier = Modifier.height(10.dp))

            TextField(
                value = email,
                onValueChange = {email = it},
                label = {Text("Email")},
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = userName,
                onValueChange = { userName = it },
                label = { Text("Enter Username") },
                modifier = Modifier.fillMaxWidth()

            )
            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = password,
                onValueChange = {password = it},
                label = {Text("Enter Password")},
                modifier = Modifier.fillMaxWidth()
            )



            Spacer(modifier = Modifier.height(8.dp))
        }

        var context = LocalContext.current

        if(userNameValid && passwordValid && emailValid) {

            Box(
                modifier = Modifier.fillMaxSize(),


            ){
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .align(Alignment.TopCenter)
                        .offset(x = 0.dp, y = 600.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,


                    ){
                    Text(
                        "Your account has been created",
                        color = Color.DarkGray
                    )
                }
            }

            LaunchedEffect(Unit) {
                delay(1000)

                /*
                reroute to tutorial page instead of home page
                WiP
                */

                val intent = Intent(context, NewUser::class.java)
                context.startActivity(intent)


            }



        }

        Button(
            onClick = {

                /*
                this is good, but as a security meassure, lets add
                password security (8+ characters)
                also need to add in whether or not someone else is using this username
                 */
                if(password.isNotEmpty() && userName.isNotEmpty() && email.isNotEmpty()) {
                    passwordValid = true
                    userNameValid = true
                    emailValid = true
                }
                else {
                    if (password.isNotEmpty())
                        passwordValid = true
                    else
                        passwordValid = false
                    if(userName.isNotEmpty())
                        userNameValid = true
                    else
                        userNameValid = false
                    if(email.isNotEmpty())
                        emailValid = true
                    else
                        emailValid = false
                }

            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.Green

            ),

            modifier = modifier
                .fillMaxWidth()
                .padding(24.dp)
                .offset(x = 0.dp, y = 498.dp)




        )
        {
            Text("Create Account")

        }
    }

}

class ForgotPassword : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleAppTheme {
                ForgotPasswordScreen()
            }
        }
    }

    @Composable
    fun ForgotPasswordScreen(modifier: Modifier = Modifier){

        var username by remember {
            mutableStateOf("")
        }

        TextField(
            value = username,
            onValueChange = { username = it},
            label =  {Text("Please enter your Username")},
            modifier = Modifier.fillMaxWidth()
        )

        /*
        need to add some functionality to basically take that username and look it up
        across a spreadsheet or something
         */
    }
}
