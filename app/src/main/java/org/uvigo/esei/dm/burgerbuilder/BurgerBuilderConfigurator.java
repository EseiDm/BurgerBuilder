package org.uvigo.esei.dm.burgerbuilder;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;

public class BurgerBuilderConfigurator {

    public static String[] INGREDIENTS = new String[]{"onion", "salad", "tomato", "chesse"};
    public static double[] INGREDIENTS_COSTS = new double[]{0.25, 0.5, 0.75, 1};

    public static String[] SERVICE_MODE = new String[]{"bar", "table", "terrace"};
    public static double[] SERVICE_COSTS = new double[]{0.25, 1, 2.50};

    private static double DISCOUNT=0.10;
    private boolean[] selectedIngredients;
    private int serviceModeIndexSelected = 0;
    private boolean applyDiscount=false;

    public BurgerBuilderConfigurator(){
        selectedIngredients = new boolean[INGREDIENTS.length];
    }

    public double calculatePrice(){
        double toret = 0;
        for (int i =0; i<selectedIngredients.length;i++){
            if(selectedIngredients[i]){
                toret+= INGREDIENTS_COSTS[i];
            }
        }

        if(toret>0){
            toret+=SERVICE_COSTS[serviceModeIndexSelected];
            if (applyDiscount){
                toret =  toret - (toret*DISCOUNT);
            }
        }
        return toret;
    }

    public boolean[] getSelectedIngredients() {
        return selectedIngredients;
    }

    public void setSelectedIngredients(boolean[] selectedIngredients) {
        this.selectedIngredients = selectedIngredients;
    }

    public void setServiceModeIndexSelected(int serviceModeIndexSelected) {
        this.serviceModeIndexSelected = serviceModeIndexSelected;
    }

    public int getServiceModeIndexSelected() {
        return serviceModeIndexSelected;
    }

    public void setApplyDiscount(boolean applyDiscount) {
        this.applyDiscount = applyDiscount;
    }

    public boolean isApplyDiscount() {
        return applyDiscount;
    }
}
