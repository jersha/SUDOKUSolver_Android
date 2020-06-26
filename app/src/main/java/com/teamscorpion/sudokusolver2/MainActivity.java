package com.teamscorpion.sudokusolver2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputFilter;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.stream.IntStream;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    public EditText[][] EditTextValues;
    public int[][] int_values;

    Button btnSOLVE, btnRESET, btnBROWSE, btnCAMERA;
    ImageView[] imageViews;
    ImageView[] imageView_dialPad;
    ImageView[] imageView_num;
    ImageView optionsbar;
    ImageView imageView_top, imageView_bottom, imageView_left, imageView_right;
    Bitmap[] images;
    Bitmap[] dialPad;
    Bitmap image_top, image_bottom, image_left, image_right;
    Bitmap image_selected;
    Bitmap[] numbers;
    Bitmap optionsbar_bt;

    public static boolean isSafe(int[][] board,
                                 int row, int col,
                                 int num)
    {
        // row has the unique (row-clash)
        for (int d = 0; d < board.length; d++) {
            // if the number we are trying to
            // place is already present in
            // that row, return false;
            if (board[row][d] == num) {
                return false;
            }
        }

        // column has the unique numbers (column-clash)
        for (int r = 0; r < board.length; r++) {
            // if the number we are trying to
            // place is already present in
            // that column, return false;

            if (board[r][col] == num) {
                return false;
            }
        }

        // corresponding square has
        // unique number (box-clash)
        int sqrt = (int)Math.sqrt(board.length);
        int boxRowStart = row - row % sqrt;
        int boxColStart = col - col % sqrt;

        for (int r = boxRowStart;
             r < boxRowStart + sqrt; r++) {
            for (int d = boxColStart;
                 d < boxColStart + sqrt; d++) {
                if (board[r][d] == num) {
                    return false;
                }
            }
        }

        // if there is no clash, it's safe
        return true;
    }

    public static boolean solveSudoku(
            int[][] board, int n)
    {
        int row = -1;
        int col = -1;
        boolean isEmpty = true;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 0) {
                    row = i;
                    col = j;

                    // we still have some remaining
                    // missing values in Sudoku
                    isEmpty = false;
                    break;
                }
            }
            if (!isEmpty) {
                break;
            }
        }

        // no empty space left
        if (isEmpty) {
            return true;
        }

        // else for each-row backtrack
        for (int num = 1; num <= n; num++) {
            if (isSafe(board, row, col, num)) {
                board[row][col] = num;
                if (solveSudoku(board, n)) {
                    // print(board, n);
                    return true;
                }
                else {
                    // replace it
                    board[row][col] = 0;
                }
            }
        }
        return false;
    }

    public static void print(
            int[][] board, int N)
    {
        // we got the answer, just print it
        for (int r = 0; r < N; r++) {
            for (int d = 0; d < N; d++) {
                System.out.print(board[r][d]);
                System.out.print(" ");
            }
            System.out.print("\n");

            if ((r + 1) % (int)Math.sqrt(N) == 0) {
                System.out.print("");
            }
        }
    }

    public void closeopen(){
        imageView_dialPad[0].setVisibility(View.GONE);
        imageView_dialPad[1].setVisibility(View.GONE);
        imageView_dialPad[2].setVisibility(View.GONE);
        imageView_dialPad[3].setVisibility(View.GONE);
        imageView_dialPad[4].setVisibility(View.GONE);
        imageView_dialPad[5].setVisibility(View.GONE);
        imageView_dialPad[6].setVisibility(View.GONE);
        imageView_dialPad[7].setVisibility(View.GONE);
        imageView_dialPad[8].setVisibility(View.GONE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                imageView_dialPad[0].setVisibility(View.VISIBLE);
                imageView_dialPad[1].setVisibility(View.VISIBLE);
                imageView_dialPad[2].setVisibility(View.VISIBLE);
                imageView_dialPad[3].setVisibility(View.VISIBLE);
                imageView_dialPad[4].setVisibility(View.VISIBLE);
                imageView_dialPad[5].setVisibility(View.VISIBLE);
                imageView_dialPad[6].setVisibility(View.VISIBLE);
                imageView_dialPad[7].setVisibility(View.VISIBLE);
                imageView_dialPad[8].setVisibility(View.VISIBLE);
            }
        },100);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.TYPE_STATUS_BAR);
//        getWindow().setFlags(
//                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
//                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
//        );
        setContentView(R.layout.activity_main);
        btnSOLVE = findViewById(R.id.btnSolve);
        btnRESET = findViewById(R.id.btnReset);
        btnBROWSE = findViewById(R.id.btnFile);
        btnCAMERA = findViewById(R.id.btnCam);
        imageViews = new ImageView[81];
        imageView_num = new ImageView[81];
        imageView_dialPad = new ImageView[9];
        images = new Bitmap[81];
        numbers = new Bitmap[9];
        dialPad = new Bitmap[9];
        optionsbar = findViewById(R.id.optionsbar);
        imageView_top = findViewById(R.id.imageViewtop);
        imageView_bottom = findViewById(R.id.imageViewbottom);
        imageView_left = findViewById(R.id.imageViewleft);
        imageView_right = findViewById(R.id.imageViewright);

        imageViews[0] = findViewById(R.id.imageView1);
        imageViews[1] = findViewById(R.id.imageView2);
        imageViews[2] = findViewById(R.id.imageView3);
        imageViews[3] = findViewById(R.id.imageView4);
        imageViews[4] = findViewById(R.id.imageView5);
        imageViews[5] = findViewById(R.id.imageView6);
        imageViews[6] = findViewById(R.id.imageView7);
        imageViews[7] = findViewById(R.id.imageView8);
        imageViews[8] = findViewById(R.id.imageView9);
        imageViews[9] = findViewById(R.id.imageView10);
        imageViews[10] = findViewById(R.id.imageView11);
        imageViews[11] = findViewById(R.id.imageView12);
        imageViews[12] = findViewById(R.id.imageView13);
        imageViews[13] = findViewById(R.id.imageView14);
        imageViews[14] = findViewById(R.id.imageView15);
        imageViews[15] = findViewById(R.id.imageView16);
        imageViews[16] = findViewById(R.id.imageView17);
        imageViews[17] = findViewById(R.id.imageView18);
        imageViews[18] = findViewById(R.id.imageView19);
        imageViews[19] = findViewById(R.id.imageView20);
        imageViews[20] = findViewById(R.id.imageView21);
        imageViews[21] = findViewById(R.id.imageView22);
        imageViews[22] = findViewById(R.id.imageView23);
        imageViews[23] = findViewById(R.id.imageView24);
        imageViews[24] = findViewById(R.id.imageView25);
        imageViews[25] = findViewById(R.id.imageView26);
        imageViews[26] = findViewById(R.id.imageView27);
        imageViews[27] = findViewById(R.id.imageView28);
        imageViews[28] = findViewById(R.id.imageView29);
        imageViews[29] = findViewById(R.id.imageView30);
        imageViews[30] = findViewById(R.id.imageView31);
        imageViews[31] = findViewById(R.id.imageView32);
        imageViews[32] = findViewById(R.id.imageView33);
        imageViews[33] = findViewById(R.id.imageView34);
        imageViews[34] = findViewById(R.id.imageView35);
        imageViews[35] = findViewById(R.id.imageView36);
        imageViews[36] = findViewById(R.id.imageView37);
        imageViews[37] = findViewById(R.id.imageView38);
        imageViews[38] = findViewById(R.id.imageView39);
        imageViews[39] = findViewById(R.id.imageView40);
        imageViews[40] = findViewById(R.id.imageView41);
        imageViews[41] = findViewById(R.id.imageView42);
        imageViews[42] = findViewById(R.id.imageView43);
        imageViews[43] = findViewById(R.id.imageView44);
        imageViews[44] = findViewById(R.id.imageView45);
        imageViews[45] = findViewById(R.id.imageView46);
        imageViews[46] = findViewById(R.id.imageView47);
        imageViews[47] = findViewById(R.id.imageView48);
        imageViews[48] = findViewById(R.id.imageView49);
        imageViews[49] = findViewById(R.id.imageView50);
        imageViews[50] = findViewById(R.id.imageView51);
        imageViews[51] = findViewById(R.id.imageView52);
        imageViews[52] = findViewById(R.id.imageView53);
        imageViews[53] = findViewById(R.id.imageView54);
        imageViews[54] = findViewById(R.id.imageView55);
        imageViews[55] = findViewById(R.id.imageView56);
        imageViews[56] = findViewById(R.id.imageView57);
        imageViews[57] = findViewById(R.id.imageView58);
        imageViews[58] = findViewById(R.id.imageView59);
        imageViews[59] = findViewById(R.id.imageView60);
        imageViews[60] = findViewById(R.id.imageView61);
        imageViews[61] = findViewById(R.id.imageView62);
        imageViews[62] = findViewById(R.id.imageView63);
        imageViews[63] = findViewById(R.id.imageView64);
        imageViews[64] = findViewById(R.id.imageView65);
        imageViews[65] = findViewById(R.id.imageView66);
        imageViews[66] = findViewById(R.id.imageView67);
        imageViews[67] = findViewById(R.id.imageView68);
        imageViews[68] = findViewById(R.id.imageView69);
        imageViews[69] = findViewById(R.id.imageView70);
        imageViews[70] = findViewById(R.id.imageView71);
        imageViews[71] = findViewById(R.id.imageView72);
        imageViews[72] = findViewById(R.id.imageView73);
        imageViews[73] = findViewById(R.id.imageView74);
        imageViews[74] = findViewById(R.id.imageView75);
        imageViews[75] = findViewById(R.id.imageView76);
        imageViews[76] = findViewById(R.id.imageView77);
        imageViews[77] = findViewById(R.id.imageView78);
        imageViews[78] = findViewById(R.id.imageView79);
        imageViews[79] = findViewById(R.id.imageView80);
        imageViews[80] = findViewById(R.id.imageView81);

        imageView_num[0] = findViewById(R.id.imagetext1);
        imageView_num[1] = findViewById(R.id.imagetext2);
        imageView_num[2] = findViewById(R.id.imagetext3);
        imageView_num[3] = findViewById(R.id.imagetext4);
        imageView_num[4] = findViewById(R.id.imagetext5);
        imageView_num[5] = findViewById(R.id.imagetext6);
        imageView_num[6] = findViewById(R.id.imagetext7);
        imageView_num[7] = findViewById(R.id.imagetext8);
        imageView_num[8] = findViewById(R.id.imagetext9);
        imageView_num[9] = findViewById(R.id.imagetext10);
        imageView_num[10] = findViewById(R.id.imagetext11);
        imageView_num[11] = findViewById(R.id.imagetext12);
        imageView_num[12] = findViewById(R.id.imagetext13);
        imageView_num[13] = findViewById(R.id.imagetext14);
        imageView_num[14] = findViewById(R.id.imagetext15);
        imageView_num[15] = findViewById(R.id.imagetext16);
        imageView_num[16] = findViewById(R.id.imagetext17);
        imageView_num[17] = findViewById(R.id.imagetext18);
        imageView_num[18] = findViewById(R.id.imagetext19);
        imageView_num[19] = findViewById(R.id.imagetext20);
        imageView_num[20] = findViewById(R.id.imagetext21);
        imageView_num[21] = findViewById(R.id.imagetext22);
        imageView_num[22] = findViewById(R.id.imagetext23);
        imageView_num[23] = findViewById(R.id.imagetext24);
        imageView_num[24] = findViewById(R.id.imagetext25);
        imageView_num[25] = findViewById(R.id.imagetext26);
        imageView_num[26] = findViewById(R.id.imagetext27);
        imageView_num[27] = findViewById(R.id.imagetext28);
        imageView_num[28] = findViewById(R.id.imagetext29);
        imageView_num[29] = findViewById(R.id.imagetext30);
        imageView_num[30] = findViewById(R.id.imagetext31);
        imageView_num[31] = findViewById(R.id.imagetext32);
        imageView_num[32] = findViewById(R.id.imagetext33);
        imageView_num[33] = findViewById(R.id.imagetext34);
        imageView_num[34] = findViewById(R.id.imagetext35);
        imageView_num[35] = findViewById(R.id.imagetext36);
        imageView_num[36] = findViewById(R.id.imagetext37);
        imageView_num[37] = findViewById(R.id.imagetext38);
        imageView_num[38] = findViewById(R.id.imagetext39);
        imageView_num[39] = findViewById(R.id.imagetext40);
        imageView_num[40] = findViewById(R.id.imagetext41);
        imageView_num[41] = findViewById(R.id.imagetext42);
        imageView_num[42] = findViewById(R.id.imagetext43);
        imageView_num[43] = findViewById(R.id.imagetext44);
        imageView_num[44] = findViewById(R.id.imagetext45);
        imageView_num[45] = findViewById(R.id.imagetext46);
        imageView_num[46] = findViewById(R.id.imagetext47);
        imageView_num[47] = findViewById(R.id.imagetext48);
        imageView_num[48] = findViewById(R.id.imagetext49);
        imageView_num[49] = findViewById(R.id.imagetext50);
        imageView_num[50] = findViewById(R.id.imagetext51);
        imageView_num[51] = findViewById(R.id.imagetext52);
        imageView_num[52] = findViewById(R.id.imagetext53);
        imageView_num[53] = findViewById(R.id.imagetext54);
        imageView_num[54] = findViewById(R.id.imagetext55);
        imageView_num[55] = findViewById(R.id.imagetext56);
        imageView_num[56] = findViewById(R.id.imagetext57);
        imageView_num[57] = findViewById(R.id.imagetext58);
        imageView_num[58] = findViewById(R.id.imagetext59);
        imageView_num[59] = findViewById(R.id.imagetext60);
        imageView_num[60] = findViewById(R.id.imagetext61);
        imageView_num[61] = findViewById(R.id.imagetext62);
        imageView_num[62] = findViewById(R.id.imagetext63);
        imageView_num[63] = findViewById(R.id.imagetext64);
        imageView_num[64] = findViewById(R.id.imagetext65);
        imageView_num[65] = findViewById(R.id.imagetext66);
        imageView_num[66] = findViewById(R.id.imagetext67);
        imageView_num[67] = findViewById(R.id.imagetext68);
        imageView_num[68] = findViewById(R.id.imagetext69);
        imageView_num[69] = findViewById(R.id.imagetext70);
        imageView_num[70] = findViewById(R.id.imagetext71);
        imageView_num[71] = findViewById(R.id.imagetext72);
        imageView_num[72] = findViewById(R.id.imagetext73);
        imageView_num[73] = findViewById(R.id.imagetext74);
        imageView_num[74] = findViewById(R.id.imagetext75);
        imageView_num[75] = findViewById(R.id.imagetext76);
        imageView_num[76] = findViewById(R.id.imagetext77);
        imageView_num[77] = findViewById(R.id.imagetext78);
        imageView_num[78] = findViewById(R.id.imagetext79);
        imageView_num[79] = findViewById(R.id.imagetext80);
        imageView_num[80] = findViewById(R.id.imagetext81);

        imageView_dialPad[0] = findViewById(R.id.imagedial1);
        imageView_dialPad[1] = findViewById(R.id.imagedial2);
        imageView_dialPad[2] = findViewById(R.id.imagedial3);
        imageView_dialPad[3] = findViewById(R.id.imagedial4);
        imageView_dialPad[4] = findViewById(R.id.imagedial5);
        imageView_dialPad[5] = findViewById(R.id.imagedial6);
        imageView_dialPad[6] = findViewById(R.id.imagedial7);
        imageView_dialPad[7] = findViewById(R.id.imagedial8);
        imageView_dialPad[8] = findViewById(R.id.imagedial9);

        dialPad[0] = BitmapFactory.decodeResource(getResources(),R.drawable.dialone);
        dialPad[1] = BitmapFactory.decodeResource(getResources(),R.drawable.dialtwo);
        dialPad[2] = BitmapFactory.decodeResource(getResources(),R.drawable.dialthree);
        dialPad[3] = BitmapFactory.decodeResource(getResources(),R.drawable.dialfour);
        dialPad[4] = BitmapFactory.decodeResource(getResources(),R.drawable.dialfive);
        dialPad[5] = BitmapFactory.decodeResource(getResources(),R.drawable.dialsix);
        dialPad[6] = BitmapFactory.decodeResource(getResources(),R.drawable.dialseven);
        dialPad[7] = BitmapFactory.decodeResource(getResources(),R.drawable.dialeight);
        dialPad[8] = BitmapFactory.decodeResource(getResources(),R.drawable.dialnine);

        numbers[0] = BitmapFactory.decodeResource(getResources(),R.drawable.num_one);
        numbers[1] = BitmapFactory.decodeResource(getResources(),R.drawable.num_two);
        numbers[2] = BitmapFactory.decodeResource(getResources(),R.drawable.num_three);
        numbers[3] = BitmapFactory.decodeResource(getResources(),R.drawable.num_four);
        numbers[4] = BitmapFactory.decodeResource(getResources(),R.drawable.num_five);
        numbers[5] = BitmapFactory.decodeResource(getResources(),R.drawable.num_six);
        numbers[6] = BitmapFactory.decodeResource(getResources(),R.drawable.num_seven);
        numbers[7] = BitmapFactory.decodeResource(getResources(),R.drawable.num_eight);
        numbers[8] = BitmapFactory.decodeResource(getResources(),R.drawable.num_nine);

        optionsbar_bt = BitmapFactory.decodeResource(getResources(),R.drawable.options);

        image_top = BitmapFactory.decodeResource(getResources(),R.drawable.top);
        image_bottom = BitmapFactory.decodeResource(getResources(),R.drawable.bottom);
        image_left = BitmapFactory.decodeResource(getResources(),R.drawable.left);
        image_right = BitmapFactory.decodeResource(getResources(),R.drawable.right);

        image_selected = BitmapFactory.decodeResource(getResources(),R.drawable.select);
        image_selected = resizeBitmap(image_selected);

        images[0] = BitmapFactory.decodeResource(getResources(),R.drawable.one);
        images[1] = BitmapFactory.decodeResource(getResources(),R.drawable.two);
        images[2] = BitmapFactory.decodeResource(getResources(),R.drawable.three);
        images[3] = BitmapFactory.decodeResource(getResources(),R.drawable.four);
        images[4] = BitmapFactory.decodeResource(getResources(),R.drawable.five);
        images[5] = BitmapFactory.decodeResource(getResources(),R.drawable.six);
        images[6] = BitmapFactory.decodeResource(getResources(),R.drawable.seven);
        images[7] = BitmapFactory.decodeResource(getResources(),R.drawable.eight);
        images[8] = BitmapFactory.decodeResource(getResources(),R.drawable.nine);
        images[9] = BitmapFactory.decodeResource(getResources(),R.drawable.ten);
        images[10] = BitmapFactory.decodeResource(getResources(),R.drawable.eleven);
        images[11] = BitmapFactory.decodeResource(getResources(),R.drawable.twelve);
        images[12] = BitmapFactory.decodeResource(getResources(),R.drawable.thirteen);
        images[13] = BitmapFactory.decodeResource(getResources(),R.drawable.fourteen);
        images[14] = BitmapFactory.decodeResource(getResources(),R.drawable.fifteen);
        images[15] = BitmapFactory.decodeResource(getResources(),R.drawable.sixteen);
        images[16] = BitmapFactory.decodeResource(getResources(),R.drawable.seventeen);
        images[17] = BitmapFactory.decodeResource(getResources(),R.drawable.eighteen);
        images[18] = BitmapFactory.decodeResource(getResources(),R.drawable.nineteen);
        images[19] = BitmapFactory.decodeResource(getResources(),R.drawable.twenty);
        images[20] = BitmapFactory.decodeResource(getResources(),R.drawable.twentyone);
        images[21] = BitmapFactory.decodeResource(getResources(),R.drawable.twentytwo);
        images[22] = BitmapFactory.decodeResource(getResources(),R.drawable.twentythree);
        images[23] = BitmapFactory.decodeResource(getResources(),R.drawable.twentyfour);
        images[24] = BitmapFactory.decodeResource(getResources(),R.drawable.twentyfive);
        images[25] = BitmapFactory.decodeResource(getResources(),R.drawable.twentysix);
        images[26] = BitmapFactory.decodeResource(getResources(),R.drawable.twentyseven);
        images[27] = BitmapFactory.decodeResource(getResources(),R.drawable.twentyeight);
        images[28] = BitmapFactory.decodeResource(getResources(),R.drawable.twentynine);
        images[29] = BitmapFactory.decodeResource(getResources(),R.drawable.thirty);
        images[30] = BitmapFactory.decodeResource(getResources(),R.drawable.thirtyone);
        images[31] = BitmapFactory.decodeResource(getResources(),R.drawable.thirtytwo);
        images[32] = BitmapFactory.decodeResource(getResources(),R.drawable.thirtythree);
        images[33] = BitmapFactory.decodeResource(getResources(),R.drawable.thirtyfour);
        images[34] = BitmapFactory.decodeResource(getResources(),R.drawable.thirtyfive);
        images[35] = BitmapFactory.decodeResource(getResources(),R.drawable.thirtysix);
        images[36] = BitmapFactory.decodeResource(getResources(),R.drawable.thirtyseven);
        images[37] = BitmapFactory.decodeResource(getResources(),R.drawable.thirtyeight);
        images[38] = BitmapFactory.decodeResource(getResources(),R.drawable.thirtynine);
        images[39] = BitmapFactory.decodeResource(getResources(),R.drawable.fourty);
        images[40] = BitmapFactory.decodeResource(getResources(),R.drawable.fourtyone);
        images[41] = BitmapFactory.decodeResource(getResources(),R.drawable.fourtytwo);
        images[42] = BitmapFactory.decodeResource(getResources(),R.drawable.fourtythree);
        images[43] = BitmapFactory.decodeResource(getResources(),R.drawable.fourtyfour);
        images[44] = BitmapFactory.decodeResource(getResources(),R.drawable.fourtyfive);
        images[45] = BitmapFactory.decodeResource(getResources(),R.drawable.fourtysix);
        images[46] = BitmapFactory.decodeResource(getResources(),R.drawable.fourtyseven);
        images[47] = BitmapFactory.decodeResource(getResources(),R.drawable.fourtyeight);
        images[48] = BitmapFactory.decodeResource(getResources(),R.drawable.fourtynine);
        images[49] = BitmapFactory.decodeResource(getResources(),R.drawable.fifty);
        images[50] = BitmapFactory.decodeResource(getResources(),R.drawable.fiftyone);
        images[51] = BitmapFactory.decodeResource(getResources(),R.drawable.fiftytwo);
        images[52] = BitmapFactory.decodeResource(getResources(),R.drawable.fiftythree);
        images[53] = BitmapFactory.decodeResource(getResources(),R.drawable.fiftyfour);
        images[54] = BitmapFactory.decodeResource(getResources(),R.drawable.fiftyfive);
        images[55] = BitmapFactory.decodeResource(getResources(),R.drawable.fiftysix);
        images[56] = BitmapFactory.decodeResource(getResources(),R.drawable.fiftyseven);
        images[57] = BitmapFactory.decodeResource(getResources(),R.drawable.fiftyeight);
        images[58] = BitmapFactory.decodeResource(getResources(),R.drawable.fiftynine);
        images[59] = BitmapFactory.decodeResource(getResources(),R.drawable.sixty);
        images[60] = BitmapFactory.decodeResource(getResources(),R.drawable.sixtyone);
        images[61] = BitmapFactory.decodeResource(getResources(),R.drawable.sixtytwo);
        images[62] = BitmapFactory.decodeResource(getResources(),R.drawable.sixtythree);
        images[63] = BitmapFactory.decodeResource(getResources(),R.drawable.sixtyfour);
        images[64] = BitmapFactory.decodeResource(getResources(),R.drawable.sixtyfive);
        images[65] = BitmapFactory.decodeResource(getResources(),R.drawable.sixtysix);
        images[66] = BitmapFactory.decodeResource(getResources(),R.drawable.sixtyseven);
        images[67] = BitmapFactory.decodeResource(getResources(),R.drawable.sixtyeight);
        images[68] = BitmapFactory.decodeResource(getResources(),R.drawable.sixtynine);
        images[69] = BitmapFactory.decodeResource(getResources(),R.drawable.seventy);
        images[70] = BitmapFactory.decodeResource(getResources(),R.drawable.seventyone);
        images[71] = BitmapFactory.decodeResource(getResources(),R.drawable.seventytwo);
        images[72] = BitmapFactory.decodeResource(getResources(),R.drawable.seventythree);
        images[73] = BitmapFactory.decodeResource(getResources(),R.drawable.seventyfour);
        images[74] = BitmapFactory.decodeResource(getResources(),R.drawable.seventyfive);
        images[75] = BitmapFactory.decodeResource(getResources(),R.drawable.seventysix);
        images[76] = BitmapFactory.decodeResource(getResources(),R.drawable.seventyseven);
        images[77] = BitmapFactory.decodeResource(getResources(),R.drawable.seventyeight);
        images[78] = BitmapFactory.decodeResource(getResources(),R.drawable.seventynine);
        images[79] = BitmapFactory.decodeResource(getResources(),R.drawable.eighty);
        images[80] = BitmapFactory.decodeResource(getResources(),R.drawable.eightyone);

        imageView_top.setImageBitmap(resizeBitmap(image_top));
        imageView_bottom.setImageBitmap(resizeBitmap(image_bottom));
        imageView_left.setImageBitmap(resizeBitmap(image_left));
        imageView_right.setImageBitmap(resizeBitmap(image_right));

        imageView_num[0].setImageBitmap(resizeBitmap(numbers[4]));

        optionsbar.setImageBitmap(resizeBitmap(optionsbar_bt));

        imageView_dialPad[0].setImageBitmap(resizeBitmap(dialPad[0]));
        imageView_dialPad[1].setImageBitmap(resizeBitmap(dialPad[1]));
        imageView_dialPad[2].setImageBitmap(resizeBitmap(dialPad[2]));
        imageView_dialPad[3].setImageBitmap(resizeBitmap(dialPad[3]));
        imageView_dialPad[4].setImageBitmap(resizeBitmap(dialPad[4]));
        imageView_dialPad[5].setImageBitmap(resizeBitmap(dialPad[5]));
        imageView_dialPad[6].setImageBitmap(resizeBitmap(dialPad[6]));
        imageView_dialPad[7].setImageBitmap(resizeBitmap(dialPad[7]));
        imageView_dialPad[8].setImageBitmap(resizeBitmap(dialPad[8]));

        imageViews[0].setImageBitmap(resizeBitmap(images[0]));
        imageViews[1].setImageBitmap(resizeBitmap(images[1]));
        imageViews[2].setImageBitmap(resizeBitmap(images[2]));
        imageViews[3].setImageBitmap(resizeBitmap(images[3]));
        imageViews[4].setImageBitmap(resizeBitmap(images[4]));
        imageViews[5].setImageBitmap(resizeBitmap(images[5]));
        imageViews[6].setImageBitmap(resizeBitmap(images[6]));
        imageViews[7].setImageBitmap(resizeBitmap(images[7]));
        imageViews[8].setImageBitmap(resizeBitmap(images[8]));
        imageViews[9].setImageBitmap(resizeBitmap(images[9]));
        imageViews[10].setImageBitmap(resizeBitmap(images[10]));
        imageViews[11].setImageBitmap(resizeBitmap(images[11]));
        imageViews[12].setImageBitmap(resizeBitmap(images[12]));
        imageViews[13].setImageBitmap(resizeBitmap(images[13]));
        imageViews[14].setImageBitmap(resizeBitmap(images[14]));
        imageViews[15].setImageBitmap(resizeBitmap(images[15]));
        imageViews[16].setImageBitmap(resizeBitmap(images[16]));
        imageViews[17].setImageBitmap(resizeBitmap(images[17]));
        imageViews[18].setImageBitmap(resizeBitmap(images[18]));
        imageViews[19].setImageBitmap(resizeBitmap(images[19]));
        imageViews[20].setImageBitmap(resizeBitmap(images[20]));
        imageViews[21].setImageBitmap(resizeBitmap(images[21]));
        imageViews[22].setImageBitmap(resizeBitmap(images[22]));
        imageViews[23].setImageBitmap(resizeBitmap(images[23]));
        imageViews[24].setImageBitmap(resizeBitmap(images[24]));
        imageViews[25].setImageBitmap(resizeBitmap(images[25]));
        imageViews[26].setImageBitmap(resizeBitmap(images[26]));
        imageViews[27].setImageBitmap(resizeBitmap(images[27]));
        imageViews[28].setImageBitmap(resizeBitmap(images[28]));
        imageViews[29].setImageBitmap(resizeBitmap(images[29]));
        imageViews[30].setImageBitmap(resizeBitmap(images[30]));
        imageViews[31].setImageBitmap(resizeBitmap(images[31]));
        imageViews[32].setImageBitmap(resizeBitmap(images[32]));
        imageViews[33].setImageBitmap(resizeBitmap(images[33]));
        imageViews[34].setImageBitmap(resizeBitmap(images[34]));
        imageViews[35].setImageBitmap(resizeBitmap(images[35]));
        imageViews[36].setImageBitmap(resizeBitmap(images[36]));
        imageViews[37].setImageBitmap(resizeBitmap(images[37]));
        imageViews[38].setImageBitmap(resizeBitmap(images[38]));
        imageViews[39].setImageBitmap(resizeBitmap(images[39]));
        imageViews[40].setImageBitmap(resizeBitmap(images[40]));
        imageViews[41].setImageBitmap(resizeBitmap(images[41]));
        imageViews[42].setImageBitmap(resizeBitmap(images[42]));
        imageViews[43].setImageBitmap(resizeBitmap(images[43]));
        imageViews[44].setImageBitmap(resizeBitmap(images[44]));
        imageViews[45].setImageBitmap(resizeBitmap(images[45]));
        imageViews[46].setImageBitmap(resizeBitmap(images[46]));
        imageViews[47].setImageBitmap(resizeBitmap(images[47]));
        imageViews[48].setImageBitmap(resizeBitmap(images[48]));
        imageViews[49].setImageBitmap(resizeBitmap(images[49]));
        imageViews[50].setImageBitmap(resizeBitmap(images[50]));
        imageViews[51].setImageBitmap(resizeBitmap(images[51]));
        imageViews[52].setImageBitmap(resizeBitmap(images[52]));
        imageViews[53].setImageBitmap(resizeBitmap(images[53]));
        imageViews[54].setImageBitmap(resizeBitmap(images[54]));
        imageViews[55].setImageBitmap(resizeBitmap(images[55]));
        imageViews[56].setImageBitmap(resizeBitmap(images[56]));
        imageViews[57].setImageBitmap(resizeBitmap(images[57]));
        imageViews[58].setImageBitmap(resizeBitmap(images[58]));
        imageViews[59].setImageBitmap(resizeBitmap(images[59]));
        imageViews[60].setImageBitmap(resizeBitmap(images[60]));
        imageViews[61].setImageBitmap(resizeBitmap(images[61]));
        imageViews[62].setImageBitmap(resizeBitmap(images[62]));
        imageViews[63].setImageBitmap(resizeBitmap(images[63]));
        imageViews[64].setImageBitmap(resizeBitmap(images[64]));
        imageViews[65].setImageBitmap(resizeBitmap(images[65]));
        imageViews[66].setImageBitmap(resizeBitmap(images[66]));
        imageViews[67].setImageBitmap(resizeBitmap(images[67]));
        imageViews[68].setImageBitmap(resizeBitmap(images[68]));
        imageViews[69].setImageBitmap(resizeBitmap(images[69]));
        imageViews[70].setImageBitmap(resizeBitmap(images[70]));
        imageViews[71].setImageBitmap(resizeBitmap(images[71]));
        imageViews[72].setImageBitmap(resizeBitmap(images[72]));
        imageViews[73].setImageBitmap(resizeBitmap(images[73]));
        imageViews[74].setImageBitmap(resizeBitmap(images[74]));
        imageViews[75].setImageBitmap(resizeBitmap(images[75]));
        imageViews[76].setImageBitmap(resizeBitmap(images[76]));
        imageViews[77].setImageBitmap(resizeBitmap(images[77]));
        imageViews[78].setImageBitmap(resizeBitmap(images[78]));
        imageViews[79].setImageBitmap(resizeBitmap(images[79]));
        imageViews[80].setImageBitmap(resizeBitmap(images[80]));

        imageView_dialPad[0].setVisibility(View.GONE);
        imageView_dialPad[1].setVisibility(View.GONE);
        imageView_dialPad[2].setVisibility(View.GONE);
        imageView_dialPad[3].setVisibility(View.GONE);
        imageView_dialPad[4].setVisibility(View.GONE);
        imageView_dialPad[5].setVisibility(View.GONE);
        imageView_dialPad[6].setVisibility(View.GONE);
        imageView_dialPad[7].setVisibility(View.GONE);
        imageView_dialPad[8].setVisibility(View.GONE);

        btnSOLVE = findViewById(R.id.btnSolve);
        btnRESET = findViewById(R.id.btnReset);
        btnBROWSE = findViewById(R.id.btnFile);
        btnCAMERA = findViewById(R.id.btnCam);

        imageView_num[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageView_num[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageView_num[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageView_num[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageView_num[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageView_num[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageView_num[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageView_num[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageView_num[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageView_num[9].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageView_num[10].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageView_num[11].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageView_num[12].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageView_num[13].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageView_num[14].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageView_num[15].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageView_num[16].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageView_num[17].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageView_num[18].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageView_num[19].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageView_num[20].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageView_num[21].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageView_num[22].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageView_num[23].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageView_num[23].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageView_num[24].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageView_num[25].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageView_num[26].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageView_num[27].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageView_num[28].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageView_num[29].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageView_num[30].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageView_num[31].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageView_num[32].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageView_num[33].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageView_num[34].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageView_num[35].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageView_num[36].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageView_num[37].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageView_num[38].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageView_num[39].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageView_num[40].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageView_num[41].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageView_num[42].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageView_num[43].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageView_num[44].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageView_num[45].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageView_num[46].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageView_num[47].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageView_num[48].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageView_num[49].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageView_num[50].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageView_num[51].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageView_num[52].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageView_num[53].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageView_num[54].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageView_num[55].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageView_num[56].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageView_num[57].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageView_num[58].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageView_num[59].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageView_num[60].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageView_num[61].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageView_num[62].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageView_num[63].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageView_num[64].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageView_num[65].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageView_num[66].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageView_num[67].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageView_num[68].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageView_num[69].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageView_num[70].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageView_num[71].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageView_num[72].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageView_num[73].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageView_num[74].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageView_num[75].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageView_num[76].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageView_num[77].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageView_num[78].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageView_num[79].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageView_num[80].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });imageViews[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageViews[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageViews[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageViews[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageViews[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageViews[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageViews[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageViews[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageViews[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageViews[9].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageViews[10].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageViews[11].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageViews[12].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageViews[13].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageViews[14].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageViews[15].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageViews[16].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageViews[17].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageViews[18].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageViews[19].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageViews[20].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageViews[21].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageViews[22].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageViews[23].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageViews[23].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageViews[24].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageViews[25].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageViews[26].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageViews[27].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageViews[28].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageViews[29].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageViews[30].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageViews[31].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageViews[32].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageViews[33].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageViews[34].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageViews[35].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageViews[36].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageViews[37].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageViews[38].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageViews[39].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageViews[40].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageViews[41].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageViews[42].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageViews[43].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageViews[44].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageViews[45].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageViews[46].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageViews[47].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageViews[48].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageViews[49].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageViews[50].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageViews[51].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageViews[52].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageViews[53].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageViews[54].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageViews[55].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageViews[56].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageViews[57].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageViews[58].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageViews[59].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageViews[60].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageViews[61].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageViews[62].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageViews[63].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageViews[64].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageViews[65].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageViews[66].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageViews[67].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageViews[68].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageViews[69].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageViews[70].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageViews[71].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageViews[72].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageViews[73].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageViews[74].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageViews[75].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageViews[76].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageViews[77].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageViews[78].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageViews[79].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        imageViews[80].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeopen();
            }
        });

        btnSOLVE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str_value;
                int_values = new int[9][9];
                for(int row = 0; row < 9; row++) {
                    for (int column = 0; column < 9; column++) {
                        str_value = EditTextValues[row][column].getText().toString();
                        if (str_value.isEmpty()) {
                            int_values[row][column] = 0;
                        } else {
                            int_values[row][column] = Integer.parseInt(str_value);
                        }
                    }
                }
                int N = int_values.length;

                if (solveSudoku(int_values, N)) {
                    for(int row = 0; row < 9; row++) {
                        for (int column = 0; column < 9; column++) {
                            str_value = Integer.toString(int_values[row][column]);
                            EditTextValues[row][column].setText(str_value);
                        }
                    }
                }
            }
        });

        btnRESET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int row = 0; row < 9; row++) {
                    for (int column = 0; column < 9; column++) {
                        EditTextValues[row][column].setText("");
                    }
                }
            }
        });
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    public Bitmap resizeBitmap(Bitmap bitmap)
    {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        float device_height = metrics.heightPixels;
        float device_width = metrics.widthPixels;
        float origWidth = bitmap.getWidth();
        float origHeight = bitmap.getHeight();
        int destWidth = (int) ((origWidth / 1440) * device_width);
        int destHeight = (int) ((origHeight / 2560) * device_height);

        // we create an scaled bitmap so it reduces the image, not just trim it
        Bitmap resized = Bitmap.createScaledBitmap(bitmap, destWidth, destHeight, false);
        return resized;
    }
}