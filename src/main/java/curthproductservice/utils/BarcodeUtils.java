package curthproductservice.utils;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
public class BarcodeUtils {

    public static String generateBarcode() {
        return String.format("prd-%06d", ((int)(Math.random() * 9000000) + 1000000));
    }
}
