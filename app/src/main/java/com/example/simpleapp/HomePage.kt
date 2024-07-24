package com.example.simpleapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import android.content.Intent
import android.graphics.Paint.Align
import android.os.PersistableBundle
import android.renderscript.ScriptGroup.Input
import androidx.activity.compose.setContent
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.layout.FlowRowScopeInstance.align

//FlowRowScopeInstance.align
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import com.example.simpleapp.ui.theme.SimpleAppTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalOf



class WorkoutLog : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_page)
        setContent {

            SimpleAppTheme {

                /*immideatly goes to log workout, but instead user should have option
                    to choose if they want to log workout or not.

                    Create a home page with the option to log workout and then direct to LogWorkout

                     */
                LogWorkout()


            }
            //Re-direct to a new page where the workouts will be logged


        }

    }

    @Composable
    fun LogWorkout(modifier: Modifier = Modifier) {

        var workoutName by remember { mutableStateOf("") }
        var sets by remember { mutableStateOf("") }
        var reps by remember { mutableStateOf("") }


        /*
        Checking that the user basically entered something in
        Can only log workout if the user has actually inputted some data
         */

        var validWorkout by remember { mutableStateOf(false) }
        var validSets by remember { mutableStateOf(false) }
        var validReps by remember { mutableStateOf(false) }

        var errorMessage by remember {
            mutableStateOf("")
        }

        var message by remember { mutableStateOf("") }


        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Top

        ) {
            TextField(
                value = workoutName,
                onValueChange = { workoutName = it },
                label = { Text("Workout Name") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = sets,
                onValueChange = { sets = it },
                label = { Text("Number of Sets") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)

            )
            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = reps,
                onValueChange = { reps = it },
                label = { Text("Number of Reps") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)

            )
            Spacer(modifier = Modifier.height(8.dp))





            Button(


                onClick = {

                    if (workoutName.isNotEmpty())
                        validWorkout = true
                    else {
                        validWorkout = false
                        errorMessage += " workout name"
                    }
                    if (reps.isNotEmpty())
                        validReps = true
                    else {
                        validReps = false
                        errorMessage += " Number of Reps"
                    }
                    if (sets.isNotEmpty())
                        validSets = true;
                    else {
                        validSets = false
                        errorMessage += " Number of sets"
                    }

                    if (workoutName.isEmpty() && reps.isEmpty() && sets.isEmpty()) {

                        errorMessage = " All fields"

                    }

                    message = "Your left$errorMessage blank"


                },

                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.Green
                ),

                modifier = Modifier.fillMaxWidth()
            )

            {

                Text("Log Workout")
            }
            var context = LocalContext.current

            if (validWorkout && validReps && validSets) {
                Text("Your workout has been Logged!")
                LaunchedEffect(Unit) {
                    kotlinx.coroutines.delay(1000)
                    val intent = Intent(context, HomePage::class.java)
                    context.startActivity(intent)

                }

            } else {
                Text(
                    text = message,
                    color = Color.Red,
                    modifier = Modifier,

                    )
            }
        }


        DisplayWorkout(workout = workoutName, Reps = reps, Sets = sets)
    }


    @Composable
    fun DisplayWorkout(workout: String, Reps: String, Sets : String, modifier: Modifier = Modifier) {
        Box(
            modifier = modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterEnd)
                    .height(50.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                Text(
                    text = "$workout : $Sets x $Reps",
                    style = TextStyle(fontSize = 25.sp),
                    modifier = modifier
                        .height(35.dp)


                )
            }
        }
    }
}


    @Composable
    fun HomeButton(){
        val context = LocalContext.current

        IconButton(onClick = {
            val intent = Intent(context, HomePage::class.java)
            context.startActivity(intent)
        }) {
            Icon(
                imageVector = Icons.Filled.Home,
                contentDescription = "Home"
            )
        }
    }



class HomePage : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_page)
        setContent{
            SimpleAppTheme {


                /*
                Create and call a composable function that adds all the different options
                1. Log Workout -> done
                2. Log Weight ->WiP
                ...
                 */
                DisplayOptions()


            }


            }
        }

        @Composable
        fun DisplayOptions(modifier:Modifier = Modifier){

            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(25.dp),
                verticalArrangement = Arrangement.Center
            ) {
                val context = LocalContext.current
                Button(

                    onClick = {
                        val intent = Intent(context, WorkoutLog::class.java)
                        context.startActivity(intent)
                    },

                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black,
                        contentColor = Color.Green
                    ),

                    modifier = Modifier.fillMaxWidth()
                )

                {

                    Text("Log new Workout  💪")
                }


                Button(
                    onClick = {

                        val intent = Intent(context, Weight::class.java)
                        context.startActivity(intent)

                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black,
                        contentColor = Color.Green
                    ),

                    modifier = Modifier.fillMaxWidth()
                )

                {
                    Text("Log Body Weight ")
                }

            }
        }
    }

class Weight : ComponentActivity() {

    //add the ability to add in your weight
    //to see weight trends (up or down? by how much?)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_page)
        setContent {
            SimpleAppTheme {
                InputWeight()
            }

        }

    }

    @Composable
    fun InputWeight( modifier: Modifier = Modifier) {

        var weight by remember { mutableStateOf("") }

        Box(
            modifier = modifier
                .fillMaxSize()
        ) {


            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterEnd)
                    .padding(24.dp),

                verticalArrangement = Arrangement.Bottom
            )
            {//WiP
                TextField(
                    value = weight,
                    onValueChange = { weight = it },
                    label = { Text("Weight") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)

                )

                Spacer(modifier = Modifier.height(8.dp))

                var context = LocalContext.current

                var weightLogged by remember {
                    mutableStateOf(false)
                }

                if (weightLogged) {
                    Text("Your weight has been logged!")

                    LaunchedEffect(Unit) {
                        kotlinx.coroutines.delay(1000)

                        val intent = Intent(context, HomePage::class.java)
                        context.startActivity(intent)
                    }


                } else {


                    Button(
                        onClick = {
                            /*
                    basically after the user clicks log weight:
                    1. Display confirmation message to confirm that weight has been logged
                    2. Then take them back to the home page
                     */
                            if (weight.isNotEmpty())
                                weightLogged = true

                        },


                        modifier = Modifier.fillMaxWidth()
                    )

                    {
                        Text("Log Weight")
                    }
                }
            }
        }
    }

    @Composable
    fun WeightConfirmed(modifier: Modifier = Modifier){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {



            Text(
                text = "Your weight has been logged!",
                style = TextStyle(fontSize = 50.sp),
                modifier = Modifier
            )
        }
    }


}


class NewUser : ComponentActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            SimpleAppTheme {
                /*
                Display a tutorial that will
                get users ask for users
                1. height
                2. weight
                3. Goals (Lose/Gain weight)

                How this data will be used:
                1. Calculate BMI and tell them what they are
                2. Track weekly progress
                3. How close user is to their goal weight

                 */

                SetUpScreen()
            }
        }
    }


    @Composable
    fun SetUpScreen(modifier: Modifier = Modifier){
        Box(
            modifier = modifier
                .fillMaxSize()
        ){
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(24.dp)
                    .align(Alignment.CenterEnd),

                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {
                Text(
                    "Welcome to MyFit",
                    color = Color.Black,
                    modifier = modifier
                        .offset(x = 0.dp, y = -50.dp),

                )

                var context = LocalContext.current
                Button(
                    onClick = {

                        val intent = Intent(context, Goals::class.java)
                        context.startActivity(intent)




                    },

                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Blue,
                        contentColor = Color.White
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
                {
                    Text("Get Started")
                }
            }


        }

    }

    /*
    fun Weight(modifier: Modifier = Modifier){

            Column(
                modifier = modifier
                    .fillMaxWidth(),
            ){
                Text(
                    "First we will get your weight"
                )

                var context = LocalContext.current

                Button(
                    onClick = {
                        val intent = Intent(context, Weight::class.java)
                        context.startActivity(intent)
                    },
                    modifier = modifier
                ){
                    Text(
                        "Lets get your weight!"
                    )
                }

        }
    }

     */
}


class Goals : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            SimpleAppTheme {
                /*
                What is their body gaols?
                1. Lose weight
                2. Gain weight
                3. Ask for their target weight

                 */

                AskGoals()
            }
        }
    }

    @Composable
    fun AskGoals(modifier: Modifier = Modifier){

        var goal by remember { mutableStateOf("") }

        Box(
            modifier = Modifier.fillMaxSize()
        ){
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopStart)
                    .padding(24.dp)
                    .offset(x = 0.dp, y = 50.dp)

            ) {
                Text(
                    "Knowing your goals can help you on your fitness journey"
                )

                Spacer(modifier = Modifier.height(100.dp))

                Text("Do you want to gain weight or lose weight?")

                Spacer(modifier = Modifier.height(25.dp))

                var context = LocalContext.current
                Button(
                    onClick = {
                        /*
                        Need to store this info on the spreadsheet
                         */

                        goal = "Gain"

                        val intent = Intent(context, Tutorial::class.java)
                        context.startActivity(intent)
                    },
                   /* colors = ButtonDefaults.buttonColors(
                        containerColor = Color.hsv(12f, 88f, 18f),
                        contentColor = Color.Black
                    )*/
                    modifier = Modifier

                ){
                    Text("Gain Weight 📈")
                }

                Button(
                    onClick = {
                        goal = "Lose"
                        val intent = Intent(context, Tutorial::class.java)
                        context.startActivity(intent)

                    },
                    modifier = Modifier
                ){
                    Text("Lose weight 📉")
                }

            }
        }
    }

}

class Split : ComponentActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            SimpleAppTheme {

                InputSplitOption()


            }
        }
    }

    @Composable
    fun InputSplitOption(modifier: Modifier = Modifier){


        var inputSplit by remember {mutableStateOf(false)} //if user wants to input their split



        /*
        Getting the users routine (Optional)
        1. Ask user if they want to input their split
        2. Ask how many days their split is
        3. Collect the order of the split and store into an array
        4. Use that array to display what day it is according to their split

         */


        Box(
            modifier = Modifier
                .fillMaxSize()
        ){



            Column(
               modifier = Modifier
                   .fillMaxWidth()
                   .align(Alignment.CenterEnd)


            ) {

                Text(
                    text = "Having a Split/Routine can help you reach your fitness goals quicker",
                    style = TextStyle(fontSize = 35.sp),
                    modifier = modifier
                        .padding(32.dp)


                )

                if(inputSplit) {
                    InputSplit()
                }


                Button(
                    onClick = {
                        inputSplit = true
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black,
                        contentColor = Color.Green
                    ),
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth()
                )
                {
                    Text("Input your Split?",
                        style = TextStyle(fontSize = 24.sp)

                    )
                }

                var context = LocalContext.current
                Button(
                    onClick = {
                        inputSplit = false

                        val intent = Intent(context, Split::class.java)
                        context.startActivity(intent)


                    },

                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black,
                        contentColor = Color.White
                    ),
                    modifier = modifier
                        .padding(10.dp)
                        .fillMaxWidth()


                ){
                    Text("Input Later",
                        style = TextStyle(fontSize = 24.sp)
                    )
                }
            }
        }







    }

    @Composable
    fun InputSplit(modifier: Modifier = Modifier){
        var numDaysString by remember{ mutableStateOf("")}
        var numDays by remember { mutableIntStateOf(0) }
        var split = remember { mutableListOf<String>()}
        var value by remember{ mutableStateOf("")}

        Box(
            modifier = Modifier
                .fillMaxSize()
        ){
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ){
                TextField(
                    value = numDaysString,
                    onValueChange = {
                        numDaysString = it
                        numDays = it.toIntOrNull() ?:0
                                    },
                    label = {Text("Input Number of days")},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 42.dp)
                )

                //create an array of size numDays



                Spacer(modifier = Modifier.height(16.dp))


                repeat(numDays){

                    inputDay(Split = split)

                }

                Spacer(modifier = Modifier.height(16.dp))

                Column {

                    var context = LocalContext.current
                    Button(
                        onClick = {
                            val intent = Intent(context, Split::class.java)
                            context.startActivity(intent)
                        }

                    ){
                        Text("Done Inputting")
                    }
                }

                if(split.isNotEmpty()){
                    Text("Muscle Groups Added:")
                    Column{
                        split.forEachIndexed{ index, day ->
                            Text(text = "${index+1}. $day")
                        }
                    }
                }


            }
        }

    }

    @Composable

    fun inputDay(Split: MutableList<String>, modifier: Modifier = Modifier) {


        /*
        dont list all the options at once, next day should come up after the button is clicked
         */


        Spacer(modifier = Modifier.height(8.dp))
        var Value by remember { mutableStateOf("") }



        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            TextField(
                value = Value,
                onValueChange = { Value = it },
                label = { Text("Input the muscle groups being targetted today") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 42.dp, vertical = 8.dp),


                )

            Button(
                onClick = {
                    Split.add(Value)

                },
                modifier = Modifier
                    .padding(horizontal = 100.dp, vertical = 5.dp)
            )
            {
                Text("Add Day")

            }

        }
    }
    }


class Tutorial : ComponentActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            inputHeight()

        }
    }


    //fun 1 to get height
    //fun 2 to get weight
    //fun 3 to get goal weight (should be optional)
    //fun 4 should compute BMI


    @Composable
    fun inputHeight(modifier: Modifier = Modifier){

        Column(
            modifier.fillMaxSize()
        ) {

            Text("First We need some information about you")

            var wantsToInput by remember { mutableStateOf(false) }

            var context = LocalContext.current

            var feetHeight by remember { mutableStateOf(0) }

            if(wantsToInput)
                inputHeight()


            Button(
                onClick = {
                    wantsToInput = true

                    //this will go towards the end
                    /*val intent  = Intent(context, Split::class.java)
                    context.startActivity(intent)

                     */


                }
            ){
                Text("Continue")
            }

            Button(
                onClick = {
                    wantsToInput = false
                }
            ){
                Text("Input later")
            }
        }

    }

    @Composable
    fun inputHeight(){
        Column(
            modifier = Modifier.fillMaxSize()
        ) {


            var heightFeet by remember { mutableStateOf("") }
            var heightInch by remember { mutableStateOf("") }
            Text(
                "First we need to get your Height",
                fontSize = 20.sp,
                modifier = Modifier
                    .offset(x = 51.dp, y = 30.dp)

            )

            TextField(
                value = heightFeet,
                onValueChange = { value -> heightFeet = value.filter { it.isDigit() } },
                label = { Text("Input your Height in feet") },
                modifier = Modifier
                    .padding(50.dp)
            )

            TextField(
                value = heightInch,
                onValueChange = {value -> heightFeet = value.filter {it.isDigit()}},
                label = {Text("Input inches")},
                modifier = Modifier

            )

            if(heightFeet.toIntOrNull() == null)
                Text(
                    "Please input a number",
                    color = Color.Red,
                    modifier = Modifier
                        .padding(horizontal = 50.dp)
                        .offset(y = -35.dp)
                        .fillMaxWidth()
                )
        }

    }
}








