package org.uvigo.esei.dm.burgerbuilder;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private BurgerBuilderConfigurator myBurgerBuilderConfigurator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myBurgerBuilderConfigurator = new BurgerBuilderConfigurator();

        Button button = findViewById(R.id.buttonIngredients);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showIngredientsDialog();
            }
        });
    }

    private void showIngredientsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select ingredients:");
        builder.setMultiChoiceItems(BurgerBuilderConfigurator.INGREDIENTS,
                myBurgerBuilderConfigurator.getSelectedIngredients(),
                new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        myBurgerBuilderConfigurator.getSelectedIngredients()[which] = isChecked;

                    }
                });
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(myBurgerBuilderConfigurator.calculatePrice()>0)
                    showServiceModeDialog();
                updateTotalCost();
            }
        });
        builder.create().show();
    }

    private void showServiceModeDialog() {

        AlertDialog.Builder builder =  new AlertDialog.Builder(this);
        builder.setTitle("Service Mode:");
        builder.setSingleChoiceItems(BurgerBuilderConfigurator.SERVICE_MODE,
                myBurgerBuilderConfigurator.getServiceModeIndexSelected(),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       myBurgerBuilderConfigurator.setServiceModeIndexSelected(which);
                    }
                });
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showDiscountDialog();
                updateTotalCost();
            }
        });
        builder.create().show();
    }

    private void showDiscountDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Discount:");
        builder.setMessage("Apply Discount?");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                myBurgerBuilderConfigurator.setApplyDiscount(true);
                updateTotalCost();
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                myBurgerBuilderConfigurator.setApplyDiscount(false);
                updateTotalCost();
            }
        });
        builder.create().show();
    }

    private void updateTotalCost() {
        TextView totalTextView = findViewById(R.id.textViewTotal);
        double total = myBurgerBuilderConfigurator.calculatePrice();
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        totalTextView.setText(decimalFormat.format(total)+"€");
    }
}