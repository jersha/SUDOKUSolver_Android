package com.teamSCORPION.sudokusolver;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    EditText ONE1, ONE2, ONE3, ONE4, ONE5, ONE6, ONE7, ONE8, ONE9;
    EditText TWO1, TWO2, TWO3, TWO4, TWO5, TWO6, TWO7, TWO8, TWO9;
    EditText THREE1, THREE2, THREE3, THREE4, THREE5, THREE6, THREE7, THREE8, THREE9;
    EditText FOUR1, FOUR2, FOUR3, FOUR4, FOUR5, FOUR6, FOUR7, FOUR8, FOUR9;
    EditText FIVE1, FIVE2, FIVE3, FIVE4, FIVE5, FIVE6, FIVE7, FIVE8, FIVE9;
    EditText SIX1, SIX2, SIX3, SIX4, SIX5, SIX6, SIX7, SIX8, SIX9;
    EditText SEVEN1, SEVEN2, SEVEN3, SEVEN4, SEVEN5, SEVEN6, SEVEN7, SEVEN8, SEVEN9;
    EditText EIGHT1, EIGHT2, EIGHT3, EIGHT4, EIGHT5, EIGHT6, EIGHT7, EIGHT8, EIGHT9;
    EditText NINE1, NINE2, NINE3, NINE4, NINE5, NINE6, NINE7, NINE8, NINE9;
    int ONEONE, ONETWO, ONETHREE, ONEFOUR, ONEFIVE, ONESIX, ONESEVEN, ONEEIGHT, ONENINE;
    int TWOONE, TWOTWO, TWOTHREE, TWOFOUR, TWOFIVE, TWOSIX, TWOSEVEN, TWOEIGHT, TWONINE;
    int THREEONE, THREETWO, THREETHREE, THREEFOUR, THREEFIVE, THREESIX, THREESEVEN, THREEEIGHT, THREENINE;
    int FOURONE, FOURTWO, FOURTHREE, FOURFOUR, FOURFIVE, FOURSIX, FOURSEVEN, FOUREIGHT, FOURNINE;
    int FIVEONE, FIVETWO, FIVETHREE, FIVEFOUR, FIVEFIVE, FIVESIX, FIVESEVEN, FIVEEIGHT, FIVENINE;
    int SIXONE, SIXTWO, SIXTHREE, SIXFOUR, SIXFIVE, SIXSIX, SIXSEVEN, SIXEIGHT, SIXNINE;
    int SEVENONE, SEVENTWO, SEVENTHREE, SEVENFOUR, SEVENFIVE, SEVENSIX, SEVENSEVEN, SEVENEIGHT, SEVENNINE;
    int EIGHTONE, EIGHTTWO, EIGHTTHREE, EIGHTFOUR, EIGHTFIVE, EIGHTSIX, EIGHTSEVEN, EIGHTEIGHT, EIGHTNINE;
    int NINEONE, NINETWO, NINETHREE, NINEFOUR, NINEFIVE, NINESIX, NINESEVEN, NINEEIGHT, NINENINE;
    Button btnSOLVE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSOLVE = findViewById(R.id.btnSolve);
        ONE1 = findViewById(R.id.ONE1);
        ONE1.setText("-1");
        ONE2 = findViewById(R.id.ONE2);
        ONE2.setText("-1");
        ONE3 = findViewById(R.id.ONE3);
        ONE3.setText("-1");
        ONE4 = findViewById(R.id.ONE4);
        ONE4.setText("-1");
        ONE5 = findViewById(R.id.ONE5);
        ONE5.setText("-1");
        ONE6 = findViewById(R.id.ONE6);
        ONE6.setText("-1");
        ONE7 = findViewById(R.id.ONE7);
        ONE7.setText("-1");
        ONE8 = findViewById(R.id.ONE8);
        ONE8.setText("-1");
        ONE9 = findViewById(R.id.ONE9);
        ONE9.setText("-1");
        ONE1.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "9" )}) ;
        ONE2.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "9" )}) ;
        ONE3.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "9" )}) ;
        ONE4.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "9" )}) ;
        ONE5.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "9" )}) ;
        ONE6.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "9" )}) ;
        ONE7.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "9" )}) ;
        ONE8.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "9" )}) ;
        ONE9.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "9" )}) ;
        TWO1 = findViewById(R.id.TWO1);
        TWO2 = findViewById(R.id.TWO2);
        TWO3 = findViewById(R.id.TWO3);
        TWO4 = findViewById(R.id.TWO4);
        TWO5 = findViewById(R.id.TWO5);
        TWO6 = findViewById(R.id.TWO6);
        TWO7 = findViewById(R.id.TWO7);
        TWO8 = findViewById(R.id.TWO8);
        TWO9 = findViewById(R.id.TWO9);
        TWO1.setText("-1");
        TWO2.setText("-1");
        TWO3.setText("-1");
        TWO4.setText("-1");
        TWO5.setText("-1");
        TWO6.setText("-1");
        TWO7.setText("-1");
        TWO8.setText("-1");
        TWO9.setText("-1");
        TWO1.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "9" )}) ;
        TWO2.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "9" )}) ;
        TWO3.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "9" )}) ;
        TWO4.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "9" )}) ;
        TWO5.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "9" )}) ;
        TWO6.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "9" )}) ;
        TWO7.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "9" )}) ;
        TWO8.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "9" )}) ;
        TWO9.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "9" )}) ;
        THREE1 = findViewById(R.id.THREE1);
        THREE2 = findViewById(R.id.THREE2);
        THREE3 = findViewById(R.id.THREE3);
        THREE4 = findViewById(R.id.THREE4);
        THREE5 = findViewById(R.id.THREE5);
        THREE6 = findViewById(R.id.THREE6);
        THREE7 = findViewById(R.id.THREE7);
        THREE8 = findViewById(R.id.THREE8);
        THREE9 = findViewById(R.id.THREE9);
        THREE1.setText("-1");
        THREE2.setText("-1");
        THREE3.setText("-1");
        THREE4.setText("-1");
        THREE5.setText("-1");
        THREE6.setText("-1");
        THREE7.setText("-1");
        THREE8.setText("-1");
        THREE9.setText("-1");
        THREE1.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "9" )}) ;
        THREE2.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "9" )}) ;
        THREE3.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "9" )}) ;
        THREE4.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "9" )}) ;
        THREE5.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "9" )}) ;
        THREE6.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "9" )}) ;
        THREE7.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "9" )}) ;
        THREE8.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "9" )}) ;
        THREE9.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "9" )}) ;
        FOUR1 = findViewById(R.id.FOUR1);
        FOUR2 = findViewById(R.id.FOUR2);
        FOUR3 = findViewById(R.id.FOUR3);
        FOUR4 = findViewById(R.id.FOUR4);
        FOUR5 = findViewById(R.id.FOUR5);
        FOUR6 = findViewById(R.id.FOUR6);
        FOUR7 = findViewById(R.id.FOUR7);
        FOUR8 = findViewById(R.id.FOUR8);
        FOUR9 = findViewById(R.id.FOUR9);
        FOUR1.setText("-1");
        FOUR2.setText("-1");
        FOUR3.setText("-1");
        FOUR4.setText("-1");
        FOUR5.setText("-1");
        FOUR6.setText("-1");
        FOUR7.setText("-1");
        FOUR8.setText("-1");
        FOUR9.setText("-1");
        FOUR1.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "9" )}) ;
        FOUR2.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "9" )}) ;
        FOUR3.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "9" )}) ;
        FOUR4.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "9" )}) ;
        FOUR5.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "9" )}) ;
        FOUR6.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "9" )}) ;
        FOUR7.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "9" )}) ;
        FOUR8.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "9" )}) ;
        FOUR9.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "9" )}) ;
        FIVE1 = findViewById(R.id.FIVE1);
        FIVE2 = findViewById(R.id.FIVE2);
        FIVE3 = findViewById(R.id.FIVE3);
        FIVE4 = findViewById(R.id.FIVE4);
        FIVE5 = findViewById(R.id.FIVE5);
        FIVE6 = findViewById(R.id.FIVE6);
        FIVE7 = findViewById(R.id.FIVE7);
        FIVE8 = findViewById(R.id.FIVE8);
        FIVE9 = findViewById(R.id.FIVE9);
        FIVE1.setText("-1");
        FIVE2.setText("-1");
        FIVE3.setText("-1");
        FIVE4.setText("-1");
        FIVE5.setText("-1");
        FIVE6.setText("-1");
        FIVE7.setText("-1");
        FIVE8.setText("-1");
        FIVE9.setText("-1");
        FIVE1.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "9" )}) ;
        FIVE2.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "9" )}) ;
        FIVE3.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "9" )}) ;
        FIVE4.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "9" )}) ;
        FIVE5.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "9" )}) ;
        FIVE6.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "9" )}) ;
        FIVE7.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "9" )}) ;
        FIVE8.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "9" )}) ;
        FIVE9.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "9" )}) ;
        SIX1 = findViewById(R.id.SIX1);
        SIX2 = findViewById(R.id.SIX2);
        SIX3 = findViewById(R.id.SIX3);
        SIX4 = findViewById(R.id.SIX4);
        SIX5 = findViewById(R.id.SIX5);
        SIX6 = findViewById(R.id.SIX6);
        SIX7 = findViewById(R.id.SIX7);
        SIX8 = findViewById(R.id.SIX8);
        SIX9 = findViewById(R.id.SIX9);
        SIX1.setText("-1");
        SIX2.setText("-1");
        SIX3.setText("-1");
        SIX4.setText("-1");
        SIX5.setText("-1");
        SIX6.setText("-1");
        SIX7.setText("-1");
        SIX8.setText("-1");
        SIX9.setText("-1");
        SIX1.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "9" )}) ;
        SIX2.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "9" )}) ;
        SIX3.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "9" )}) ;
        SIX4.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "9" )}) ;
        SIX5.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "9" )}) ;
        SIX6.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "9" )}) ;
        SIX7.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "9" )}) ;
        SIX8.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "9" )}) ;
        SIX9.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "9" )}) ;
        SEVEN1 = findViewById(R.id.SEVEN1);
        SEVEN2 = findViewById(R.id.SEVEN2);
        SEVEN3 = findViewById(R.id.SEVEN3);
        SEVEN4 = findViewById(R.id.SEVEN4);
        SEVEN5 = findViewById(R.id.SEVEN5);
        SEVEN6 = findViewById(R.id.SEVEN6);
        SEVEN7 = findViewById(R.id.SEVEN7);
        SEVEN8 = findViewById(R.id.SEVEN8);
        SEVEN9 = findViewById(R.id.SEVEN9);
        SEVEN1.setText("-1");
        SEVEN2.setText("-1");
        SEVEN3.setText("-1");
        SEVEN4.setText("-1");
        SEVEN5.setText("-1");
        SEVEN6.setText("-1");
        SEVEN7.setText("-1");
        SEVEN8.setText("-1");
        SEVEN9.setText("-1");
        SEVEN1.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "9" )}) ;
        SEVEN2.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "9" )}) ;
        SEVEN3.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "9" )}) ;
        SEVEN4.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "9" )}) ;
        SEVEN5.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "9" )}) ;
        SEVEN6.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "9" )}) ;
        SEVEN7.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "9" )}) ;
        SEVEN8.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "9" )}) ;
        SEVEN9.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "9" )}) ;
        EIGHT1 = findViewById(R.id.EIGHT1);
        EIGHT2 = findViewById(R.id.EIGHT2);
        EIGHT3 = findViewById(R.id.EIGHT3);
        EIGHT4 = findViewById(R.id.EIGHT4);
        EIGHT5 = findViewById(R.id.EIGHT5);
        EIGHT6 = findViewById(R.id.EIGHT6);
        EIGHT7 = findViewById(R.id.EIGHT7);
        EIGHT8 = findViewById(R.id.EIGHT8);
        EIGHT9 = findViewById(R.id.EIGHT9);
        EIGHT1.setText("-1");
        EIGHT2.setText("-1");
        EIGHT3.setText("-1");
        EIGHT4.setText("-1");
        EIGHT5.setText("-1");
        EIGHT6.setText("-1");
        EIGHT7.setText("-1");
        EIGHT8.setText("-1");
        EIGHT9.setText("-1");
        EIGHT1.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "9" )}) ;
        EIGHT2.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "9" )}) ;
        EIGHT3.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "9" )}) ;
        EIGHT4.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "9" )}) ;
        EIGHT5.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "9" )}) ;
        EIGHT6.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "9" )}) ;
        EIGHT7.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "9" )}) ;
        EIGHT8.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "9" )}) ;
        EIGHT9.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "9" )}) ;
        NINE1 = findViewById(R.id.NINE1);
        NINE2 = findViewById(R.id.NINE2);
        NINE3 = findViewById(R.id.NINE3);
        NINE4 = findViewById(R.id.NINE4);
        NINE5 = findViewById(R.id.NINE5);
        NINE6 = findViewById(R.id.NINE6);
        NINE7 = findViewById(R.id.NINE7);
        NINE8 = findViewById(R.id.NINE8);
        NINE9 = findViewById(R.id.NINE9);
        NINE1.setText("-1");
        NINE2.setText("-1");
        NINE3.setText("-1");
        NINE4.setText("-1");
        NINE5.setText("-1");
        NINE6.setText("-1");
        NINE7.setText("-1");
        NINE8.setText("-1");
        NINE9.setText("-1");
        NINE1.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "9" )}) ;
        NINE2.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "9" )}) ;
        NINE3.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "9" )}) ;
        NINE4.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "9" )}) ;
        NINE5.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "9" )}) ;
        NINE6.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "9" )}) ;
        NINE7.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "9" )}) ;
        NINE8.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "9" )}) ;
        NINE9.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "9" )}) ;

        btnSOLVE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = ONE1.getText().toString();
                ONEONE = Integer.parseInt(value);
                value = ONE2.getText().toString();
                ONETWO = Integer.parseInt(value);
                value = ONE3.getText().toString();
                ONETHREE = Integer.parseInt(value);
                value = ONE4.getText().toString();
                ONEFOUR = Integer.parseInt(value);
                value = ONE5.getText().toString();
                ONEFIVE = Integer.parseInt(value);
                value = ONE6.getText().toString();
                ONESIX = Integer.parseInt(value);
                value = ONE7.getText().toString();
                ONESEVEN = Integer.parseInt(value);
                value = ONE8.getText().toString();
                ONEEIGHT = Integer.parseInt(value);
                value = ONE9.getText().toString();
                ONENINE = Integer.parseInt(value);

                value = TWO1.getText().toString();
                TWOONE = Integer.parseInt(value);
                value = TWO2.getText().toString();
                TWOTWO = Integer.parseInt(value);
                value = TWO3.getText().toString();
                TWOTHREE = Integer.parseInt(value);
                value = TWO4.getText().toString();
                TWOFOUR = Integer.parseInt(value);
                value = TWO5.getText().toString();
                TWOFIVE = Integer.parseInt(value);
                value = TWO6.getText().toString();
                TWOSIX = Integer.parseInt(value);
                value = TWO7.getText().toString();
                TWOSEVEN = Integer.parseInt(value);
                value = TWO8.getText().toString();
                TWOEIGHT = Integer.parseInt(value);
                value = TWO9.getText().toString();
                TWONINE = Integer.parseInt(value);

                value = THREE1.getText().toString();
                THREEONE = Integer.parseInt(value);
                value = THREE2.getText().toString();
                THREETWO = Integer.parseInt(value);
                value = THREE3.getText().toString();
                THREETHREE = Integer.parseInt(value);
                value = THREE4.getText().toString();
                THREEFOUR = Integer.parseInt(value);
                value = THREE5.getText().toString();
                THREEFIVE = Integer.parseInt(value);
                value = THREE6.getText().toString();
                THREESIX = Integer.parseInt(value);
                value = THREE7.getText().toString();
                THREESEVEN = Integer.parseInt(value);
                value = THREE8.getText().toString();
                THREEEIGHT = Integer.parseInt(value);
                value = THREE9.getText().toString();
                THREENINE = Integer.parseInt(value);

                value = FOUR1.getText().toString();
                FOURONE = Integer.parseInt(value);
                value = FOUR2.getText().toString();
                FOURTWO = Integer.parseInt(value);
                value = FOUR3.getText().toString();
                FOURTHREE = Integer.parseInt(value);
                value = FOUR4.getText().toString();
                FOURFOUR = Integer.parseInt(value);
                value = FOUR5.getText().toString();
                FOURFIVE = Integer.parseInt(value);
                value = FOUR6.getText().toString();
                FOURSIX = Integer.parseInt(value);
                value = FOUR7.getText().toString();
                FOURSEVEN = Integer.parseInt(value);
                value = FOUR8.getText().toString();
                FOUREIGHT = Integer.parseInt(value);
                value = FOUR9.getText().toString();
                FOURNINE = Integer.parseInt(value);

                value = FIVE1.getText().toString();
                FIVEONE = Integer.parseInt(value);
                value = FIVE2.getText().toString();
                FIVETWO = Integer.parseInt(value);
                value = FIVE3.getText().toString();
                FIVETHREE = Integer.parseInt(value);
                value = FIVE4.getText().toString();
                FIVEFOUR = Integer.parseInt(value);
                value = FIVE5.getText().toString();
                FIVEFIVE = Integer.parseInt(value);
                value = FIVE6.getText().toString();
                FIVESIX = Integer.parseInt(value);
                value = FIVE7.getText().toString();
                FIVESEVEN = Integer.parseInt(value);
                value = FIVE8.getText().toString();
                FIVEEIGHT = Integer.parseInt(value);
                value = FIVE9.getText().toString();
                FIVENINE = Integer.parseInt(value);

                value = SIX1.getText().toString();
                SIXONE = Integer.parseInt(value);
                value = SIX2.getText().toString();
                SIXTWO = Integer.parseInt(value);
                value = SIX3.getText().toString();
                SIXTHREE = Integer.parseInt(value);
                value = SIX4.getText().toString();
                SIXFOUR = Integer.parseInt(value);
                value = SIX5.getText().toString();
                SIXFIVE = Integer.parseInt(value);
                value = SIX6.getText().toString();
                SIXSIX = Integer.parseInt(value);
                value = SIX7.getText().toString();
                SIXSEVEN = Integer.parseInt(value);
                value = SIX8.getText().toString();
                SIXEIGHT = Integer.parseInt(value);
                value = SIX9.getText().toString();
                SIXNINE = Integer.parseInt(value);

                value = SEVEN1.getText().toString();
                SEVENONE = Integer.parseInt(value);
                value = SEVEN2.getText().toString();
                SEVENTWO = Integer.parseInt(value);
                value = SEVEN3.getText().toString();
                SEVENTHREE = Integer.parseInt(value);
                value = SEVEN4.getText().toString();
                SEVENFOUR = Integer.parseInt(value);
                value = SEVEN5.getText().toString();
                SEVENFIVE = Integer.parseInt(value);
                value = SEVEN6.getText().toString();
                SEVENSIX = Integer.parseInt(value);
                value = SEVEN7.getText().toString();
                SEVENSEVEN = Integer.parseInt(value);
                value = SEVEN8.getText().toString();
                SEVENEIGHT = Integer.parseInt(value);
                value = SEVEN9.getText().toString();
                SEVENNINE = Integer.parseInt(value);

                value = EIGHT1.getText().toString();
                EIGHTONE = Integer.parseInt(value);
                value = EIGHT2.getText().toString();
                EIGHTTWO = Integer.parseInt(value);
                value = EIGHT3.getText().toString();
                EIGHTTHREE = Integer.parseInt(value);
                value = EIGHT4.getText().toString();
                EIGHTFOUR = Integer.parseInt(value);
                value = EIGHT5.getText().toString();
                EIGHTFIVE = Integer.parseInt(value);
                value = EIGHT6.getText().toString();
                EIGHTSIX = Integer.parseInt(value);
                value = EIGHT7.getText().toString();
                EIGHTSEVEN = Integer.parseInt(value);
                value = EIGHT8.getText().toString();
                EIGHTEIGHT = Integer.parseInt(value);
                value = EIGHT9.getText().toString();
                EIGHTNINE = Integer.parseInt(value);

                value = NINE1.getText().toString();
                NINEONE = Integer.parseInt(value);
                value = NINE2.getText().toString();
                NINETWO = Integer.parseInt(value);
                value = NINE3.getText().toString();
                NINETHREE = Integer.parseInt(value);
                value = NINE4.getText().toString();
                NINEFOUR = Integer.parseInt(value);
                value = NINE5.getText().toString();
                NINEFIVE = Integer.parseInt(value);
                value = NINE6.getText().toString();
                NINESIX = Integer.parseInt(value);
                value = NINE7.getText().toString();
                NINESEVEN = Integer.parseInt(value);
                value = NINE8.getText().toString();
                NINEEIGHT = Integer.parseInt(value);
                value = NINE9.getText().toString();
                NINENINE = Integer.parseInt(value);
            }
        });
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}