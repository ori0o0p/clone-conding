package com.example.instagramclonecoding.domain.share.qr;

import org.springframework.stereotype.Service;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.awt.image.BufferedImage;

@Service
public class QrGenerateService {

    public BufferedImage execute(String url)  {

        if (url.isBlank()) {
            throw new RuntimeException("url이 비어있어요");
        }
        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();

            BitMatrix bitMatrix = qrCodeWriter.encode(
                    url,
                    BarcodeFormat.QR_CODE,
                    200, 200
            );
            return MatrixToImageWriter.toBufferedImage(bitMatrix);
        } catch (WriterException e) {
            throw new RuntimeException("qr 생성 실패");
        }
    }

}