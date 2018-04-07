package com.example.karthik.myorder;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.provider.AlarmClock.EXTRA_MESSAGE;
import static java.net.Proxy.Type.HTTP;

public class MainActivity extends AppCompatActivity {
    private static final String MAIN_ACTIVITY_TAG = "MainActivity";
    public static final String EXTRA_MESSAGE = "message";
    final int PIZZA_PRICE = 6;
    final int EXTRA_CHEESE_PRICE = 1;
    final int CHICKEN_PRICE = 2;
    int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

       /* Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("geo:47.6,-122.3"));
        if (intent.resolveActivity(getPackageManager()) !=null){
            startActivity(intent);
        }*/

//        get user input
        EditText userInputNameView = (EditText) findViewById(R.id.user_input);
        String userInputName = userInputNameView.getText().toString();
//        check if whipped cream is selected
        CheckBox extraCheese = (CheckBox) findViewById(R.id.extraCheese_checked);
        boolean hasExtraCheese = extraCheese.isChecked();
        //        check if chocolate is selected
        CheckBox chicken = (CheckBox) findViewById(R.id.chicken_checked);
        boolean hasChicken = chicken.isChecked();
//        calculate and store the total price
        float totalPrice = calculatePrice(hasExtraCheese, hasChicken);
//        create and store the order summary
        String orderSummaryMessage = createOrderSummary(userInputName, hasExtraCheese, hasChicken, totalPrice);

// Write the relevant code for making the buttons work(i.e impelement the implicit and explicit intents
        Intent intent = new Intent(this, summary.class);
        intent.putExtra(EXTRA_MESSAGE, orderSummaryMessage);
        startActivity(intent);
    }
    public void submitSummary(View view) {
//        get user input
        EditText userInputNameView = (EditText) findViewById(R.id.user_input);
        String userInputName = userInputNameView.getText().toString();
        EditText EmailId = (EditText) findViewById(R.id.user_ID);
        String emailID = EmailId.getText().toString();

//        check if whipped cream is selected
        CheckBox extraCheese = (CheckBox) findViewById(R.id.extraCheese_checked);
        boolean hasExtraCheese = extraCheese.isChecked();
        //        check if chocolate is selected
        CheckBox chicken = (CheckBox) findViewById(R.id.chicken_checked);
        boolean hasChicken = chicken.isChecked();
//        calculate and store the total price
        float totalPrice = calculatePrice(hasExtraCheese, hasChicken);
//        create and store the order summary
        String orderSummaryMessage = createOrderSummary(userInputName, hasExtraCheese, hasChicken, totalPrice);

// Write the relevant code for making the buttons work(i.e impelement the implicit and explicit intents
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
// The intent does not have a URI, so declare the "text/plain" MIME type
        emailIntent.setType("plain/text");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {emailID}); // recipients
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Order Summary");
        emailIntent.putExtra(Intent.EXTRA_TEXT, orderSummaryMessage);
        startActivity(Intent.createChooser(emailIntent, ""));
    }
    private String boolToString(boolean bool){
        return bool?(getString(R.string.yes)):(getString(R.string.no));

    }

    private String createOrderSummary(String userInputName, boolean hasExtraCheese, boolean hasChicken, float price) {
        String orderSummaryMessage = getString(R.string.order_summary_name,userInputName) +"\n"+
                getString(R.string.order_summary_extra_cheese,boolToString(hasExtraCheese))+"\n"+
                getString(R.string.order_summary_chicken,boolToString(hasChicken)) +"\n"+
                getString(R.string.order_summary_quantity,quantity)+"\n"+
                getString(R.string.order_summary_total_price,price) +"\n";
        return orderSummaryMessage;
    }

    /**
     * Method to calculate the total price
     *
     * @return total Price
     */
    private float calculatePrice(boolean hasExtraCheese, boolean hasChicken) {
        int basePrice = PIZZA_PRICE;
        if (hasExtraCheese) {
            basePrice += EXTRA_CHEESE_PRICE;
        }
        if (hasChicken) {
            basePrice += CHICKEN_PRICE;
        }

        return quantity * basePrice;

    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);

    }


    /**
     * This method increments the quantity of coffee cups by one
     *
     * @param view on passes the view that we are working with to the method
     */

    public void increment(View view) {
        if (quantity < 100) {
            quantity = quantity + 1;
            display(quantity);
        } else {

            Log.i("MainActivity", "Please select less than one hundred cups of coffee");
            Context context = getApplicationContext();
            String lowerLimitToast = getString(R.string.too_much_coffee);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, lowerLimitToast, duration);
            toast.show();
            return;

        }
    }

    /**
     * This method decrements the quantity of coffee cups by one
     *
     * @param view passes on the view that we are working with to the method
     */
    public void decrement(View view) {
        if (quantity > 1) {
            quantity = quantity - 1;
            display(quantity);
        } else {

            Log.i("MainActivity", "Please select atleast one cup of coffee");
            Context context = getApplicationContext();
            String upperLimitToast = getString(R.string.too_little_coffee);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, upperLimitToast, duration);
            toast.show();
            return;


        }
    }


}
