package curthproductservice.model;

import lombok.Getter;

@Getter
public enum ProductType {
   BASIC("BASIC"),
   CLASSIC("CLASSIC"),
   PREMIUM("PREMIUM"),
   PREMIUM_PLUS("PREMIUM PLUS"),
   SUPER_BASIC("SUPER BASIC"),
   SUPER_PLUS("SUPER PLUS");

   private final String productType;

   ProductType(String productType) {
      this.productType = productType;
   }

    public static ProductType fromProductTypeName(String name) {
      for (ProductType type : ProductType.values()) {
         if (type.productType.equalsIgnoreCase(name)) {
            return type;
         }
      }
      throw new IllegalArgumentException("Unknown product type: " + name);
   }
}
