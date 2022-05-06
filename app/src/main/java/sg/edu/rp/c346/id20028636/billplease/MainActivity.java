package sg.edu.rp.c346.id20028636.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    EditText amount;
    EditText numPax;
    ToggleButton svs;
    ToggleButton gst;
    TextView totalBill;
    TextView eachPays;
    Button split;
    Button reset;
    EditText discount;
    RadioGroup paymentoption;
    RadioButton cash;
    RadioButton paynow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amount = findViewById(R.id.editInputAmount);
        numPax = findViewById(R.id.editInputNumPax);
        totalBill = findViewById(R.id.textTotalBill);
        eachPays = findViewById(R.id.textEachPays);
        svs = findViewById(R.id.tbSvs);
        gst = findViewById(R.id.tbGst);
        split = findViewById(R.id.split);
        reset = findViewById(R.id.reset);
        discount = findViewById(R.id.editInputDiscount);
        paymentoption = findViewById(R.id.paymentoption);
        cash = findViewById(R.id.cash);
        paynow = findViewById(R.id.paynow);

        split.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(amount.getText().toString().trim().length()!=0 && numPax.getText().toString().trim().length()!=0) {
                    double newAmt = 0.0;
                    if (!svs.isChecked() && !gst.isChecked()) {
                        newAmt = Double.parseDouble(amount.getText().toString());
                    } else if (svs.isChecked() && !gst.isChecked()) {
                        newAmt = Double.parseDouble(amount.getText().toString()) * 1.1;
                    } else if (!svs.isChecked() && gst.isChecked()) {
                        newAmt = Double.parseDouble(amount.getText().toString()) * 1.07;
                    } else {
                        newAmt = Double.parseDouble(amount.getText().toString()) * 1.17;
                    }
                    //Discount
                    if (discount.getText().toString().trim().length() != 0) {
                        newAmt *= 1 - Double.parseDouble(discount.getText().toString()) / 100;
                    }
                    double numofpax = Double.parseDouble(numPax.getText().toString());
                    double split_amount = newAmt / numofpax;
                    totalBill.setText("Total Bill: $" + String.format("%.2f", newAmt));
                    int numPerson = Integer.parseInt(numPax.getText().toString());
                    if(cash.isChecked())
                    {
                        String result = String.format("Total Bill: $%.2f\nEach Pays: $%.2f in cash",newAmt,split_amount);
                        ResultView.setText(result);
                    }
                    else
                    {
                        String result = String.format("Total Bill: $%.2f\nEach Pays: $%.2f via PayNow to 91111111",newAmt,split_amount);
                        ResultView.setText(result);
                    }


            }
        };

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amount.setText("");
                numPax.setText("");
                svs.setChecked(false);
                gst.setChecked(false);
                discount.setText("");
                cash.setChecked(true);
                paynow.setChecked(false);
                ResultView.setText("");
            }
        };
    });

}}