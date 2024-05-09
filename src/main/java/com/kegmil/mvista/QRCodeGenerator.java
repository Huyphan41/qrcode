package com.kegmil.mvista;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.io.File;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class QRCodeGenerator {

  public static void generateQRCodeImage(String text, int width, int height, String filePath) throws Exception {
    Map<EncodeHintType, Object> hints = new HashMap<>();
    hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
    hints.put(EncodeHintType.MARGIN, 1); // Default margin is 4
    hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

    BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, hints);

    Path path = new File(filePath).toPath();
    MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
  }

  public static void main(String[] args) {
    String secureURL = URLGenerator.generateSecureURL("http://dev-kh-7794-web-app.kegmil.co/public/asset");
    try {
      generateQRCodeImage(secureURL, 350, 350, "SecureQRCode.png");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
