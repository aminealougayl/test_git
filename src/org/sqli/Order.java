package org.sqli;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

public class Order {

    private final String productCode;
    private final int quantity;
    private final String promotionCode;

    public Order(String productCode, int quantity, String promotionCode){
        this.productCode = productCode;
        this.quantity = quantity;
        this.promotionCode = promotionCode;
    }

    public boolean hasDiscount() {
        return isNotBlank(promotionCode);
    }

    public void printUsing(Printer printer) {
        StringBuilder builder = new StringBuilder();
        builder.append("Product Code : ").append(productCode).append(", Quantity : ").append(quantity);
        if (isNotBlank(promotionCode)){
            builder.append(", Promotion Code : ").append(promotionCode);
        }
        printer.print(builder.toString());
    }

    @Override
    public boolean equals(Object obj) {
        return this.productCode.equals(((Order)obj).productCode);
    }

    @Override
    public int hashCode() {
        return productCode.hashCode();
    }
}
