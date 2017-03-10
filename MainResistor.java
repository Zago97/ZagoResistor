package lab0_203_33.uwaterloo.ca.zagoresistor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Button;

public class WorkingTest extends AppCompatActivity {
  //creating global variables to be used throughout the program
    TextView output; //outputs the resistance and tolerance valuec
    EditText input; //getting the user to type the corresponding resistance code
    Button   convert; // button which convert the input into output
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        LinearLayout ll;                //set a name for the layoutsetContentView(R.layout.activity_working_test);
        ll = (LinearLayout)findViewById(R.id.activity_main);    //linking the layout to the app
        ll.setOrientation(LinearLayout.VERTICAL);

        //creating the output/input/button based on xml layout for position
        output = (TextView) findViewById(R.id.textView);
        input = (EditText) findViewById(R.id.editText) ;
        convert = (Button) findViewById(R.id.button);


        output.setTextSize(10);

        output.setText("Please enter 4 characters based off the graph below");
        //A handler for clicking on the button
        convert.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        //enables the function to convert only if there are only 4 characters
                        if(input.getText().toString().length()==4)
                        recordData();
                        //else error messages telling what the user should do
                        else
                        output.setText("ERROR: ENTER 4 CHARACTER; based off the graph below");
                    }
                }
        );
    }

    //recordData is the function which converts the input from user to its output
    public void recordData() {

        //declaring variables needed
        char x,tol;
        double t;
        //converting the input from user into a string
        String inputText = input.getText().toString();
        int i =0;
        double resistance =0;
        double tolerance1 =0;

        //4 integer array
        int [] band = new int[4];
        //int band[4] = {0};

        //boolean flag keeping track of correct input
        boolean flag = true;

        boolean flagtt = true; //tolerance flag if 0 tol
        //"Enter the four resistor color codes: ";

        //while loop for the first three bands
        while(i<3){
               //getting a char character based on String character at a certain position
               //0,1,2 which represents the first three characters
               x = inputText.charAt(i);

            //logic based on the graph which i have displayed on the app
            //each band has a certain value based off colour
            switch(x){
                case 'k':{
                    band[i]=0;
                    break;}
                case 'n':{
                    band[i]=1;
                    break;}
                case 'R':{
                    band[i]=2;
                    break;}
                case 'O':{
                    band[i]=3;
                    break;}
                case 'Y':{
                    band[i]=4;
                    break;}
                case 'G':{
                    band[i]=5;
                    break;}
                case 'B':{
                    band[i]=6;
                    break;}
                case 'V':{
                    band[i]=7;
                    break;}
                case 'y':{
                    band[i]=8;
                    break;}

                //note the false statement based on graph; no value for certain character at certain
                    //positions
                case 'W':{
                    band[i]=9;
                    if(i==3)
                        flag = false;
                    break;}
                case 'd':{
                    band[i]=(-1);
                    if(i==0||i==1)
                        flag =false;
                    break;}
                case 'S':{
                    band[i]=(-2);
                    if(i==0||i==1)
                        flag =false;
                    break;}
                case 'N':{
                    band[i]=0;
                    if(i==0||i==1)
                        flag =false;
                    break;}
                default:{
                    flag = false;
                }

            }i++;
        }

        //getting the tolerance for the last character
        tol = inputText.charAt(3);
        double cookie=0;

        //calculating the total resistance based off first three bands
        resistance = (band[0]*10+band[1])*Math.pow(10,band[2]);


        //if else statement to calculate tolerance based off last colour band
        if(tol=='n'){

            cookie=0.01*resistance;
        }
        else if(tol=='R'){

            cookie=0.02*resistance;
        }
        else if(tol=='G'){

            cookie=0.05*resistance;
        }
        else if(tol=='B'){

            cookie=0.0025*resistance;
        }
        else if(tol=='V'){

            cookie=0.001*resistance;
        }
        else if(tol=='y'){

            cookie=0.0005*resistance;
        }
        else if(tol=='d'){
            cookie=0.05*resistance;
        }
        else if(tol=='S'){

            cookie=0.1*resistance;
        }
        else if(tol=='N'){
            cookie=0.2*resistance;

        }

        else{
             flagtt = false;
        }

        //decided to not have a false error if the last band is not one of these colours
        //the user should be able to pick up and know that it is
        //impossible to have 0 resistance tolerance in a resistance
        //if they have basic circuit and science knowledge;


      //displaying corresponding output

        if(flag) {
            if(flagtt)
                output.setText("Resistance is " + resistance + " ohms (+/- " + cookie + " ohms tolerance)");
            else
                output.setText("Check last band colour(error = 0 toloerance +\n+" +
                        "\"Resistance is \" + resistance + \" ohms (+/- \" + cookie + \" ohms tolerance)\"");
        }
        else if(!flag)
            output.setText("Error:Invalid color code entered!");
    }
}
