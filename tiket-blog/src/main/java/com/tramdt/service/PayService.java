package com.tramdt.service;

import com.tramdt.comon.MyConstants;
import com.tramdt.config.sercurity.Config;
import com.tramdt.dto.PayInfo;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class PayService {
    public String payWithVNPAY(PayInfo payInfo, HttpServletRequest request) throws UnsupportedEncodingException {
        int amount = payInfo.quantity *100;

        Map<String,String> vnp_params = new HashMap<>();
        vnp_params.put("vnp_Version",MyConstants.vnp_Version);
        vnp_params.put("vnp_Command", MyConstants.vnp_Command);
        vnp_params.put("vnp_TmnCode", MyConstants.vnp_TmnCode);
        vnp_params.put("vnp_Amount", String.valueOf(amount));

        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String vnp_CreateDate = time.format(formatter);
        vnp_params.put("vnp_CreateDate",vnp_CreateDate);

        vnp_params.put("vnp_CurrCode",MyConstants.vnp_CurrCode);
        vnp_params.put("vnp_IpAddr",Config.getIpAddress(request));
        vnp_params.put("vnp_Locale",MyConstants.vnp_Locale);
        vnp_params.put("vnp_OrderInfo", Config.description);
        vnp_params.put("vnp_OrderType", Config.vnp_OrderType);
        vnp_params.put("vnp_ReturnUrl",Config.vnp_ReturnUrl);
        vnp_params.put("vnp_TxnRef", Config.getOTP(8));

        List fieldList = new ArrayList(vnp_params.keySet());
        Collections.sort(fieldList);

        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();

        Iterator itr =  fieldList.iterator();
        while (itr.hasNext()){
            String fieldName = (String) itr.next();
            String fieldValue = vnp_params.get(fieldName);
            if(fieldValue!=null && (fieldValue.length()>0)){
                hashData.append(fieldName);
                hashData.append("=");
                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));

                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                query.append("=");
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));

                if(itr.hasNext()){
                    query.append("&");
                    hashData.append("&");
                }
            }
        }
        String queryUrl = query.toString();
        String vnp_SecureHash = Config.hmacSHA512(MyConstants.vnp_HashSecret,hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = MyConstants.vnp_Url + "?" + queryUrl;
        System.out.println("paymentUrl" + paymentUrl);
        return paymentUrl;
    }

}