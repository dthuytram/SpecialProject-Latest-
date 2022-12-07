package com.tramdt.controller;

import com.tramdt.comon.MyConstants;
import com.tramdt.comon.Security_Email;
import com.tramdt.config.sercurity.SmtpAuthenticator;
import com.tramdt.dto.*;
import com.tramdt.dto.IDto.SeatTicketDto;
import com.tramdt.dto.IDto.TicketDto;
import com.tramdt.model.Flight;
import com.tramdt.model.SeatType;
import com.tramdt.model.Ticket;
import com.tramdt.model.TicketIdByEmail;
import com.tramdt.service.ITicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import org.springframework.data.domain.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/ticket")
public class TicketController {
    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private SmtpAuthenticator smtpAuthenticator;

    @Autowired
    ITicketService ticketService;

    double totalPriceSendMail;

    /**
     * Method name: listAllTicketListByCustomerId
     * <Pre>
     *     Lấy danh sách ticket by customer Id
     * </Pre>
     * @param id
     * @param page
     * @return
     */
    @GetMapping("/list/{id}")
    public ResponseEntity<Page<Ticket>> listAllTicketListByCustomerId(@PathVariable Long id, @RequestParam(value = "page", defaultValue = "0") int page) {
        Page<Ticket> ticketList = ticketService.findAllTicketsByCustomerIdPage(id, PageRequest.of(page, 5));
        if (ticketList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<>(ticketList, HttpStatus.OK);
    }

    //    SonNh lấy danh sách ticket by customer Id

    /**
     *
     * @param id
     * @param page
     * @return
     */
    @GetMapping("/listHistory/{id}")
    public ResponseEntity<Page<Ticket>> listHistoryTicketListByCustomerId(@PathVariable("id") Long id, @RequestParam(value = "page", defaultValue = "0") int page) {
        Page<Ticket> ticketList = ticketService.findHistoryTicketsByCustomerIdPage(id, PageRequest.of(page, 5));
        if (ticketList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<>(ticketList, HttpStatus.OK);
    }


    //    SonNh lấy danh sách ticket by customer email
    @GetMapping("/listEmail/{email}")
    public ResponseEntity<Page<Ticket>> listAllTicketListByCustomerEmail(@PathVariable(value = "email") String customerEmail,
                                                                         @RequestParam(value = "page", defaultValue = "0") int page,
                                                                         @RequestParam(name = "ROLE", defaultValue = "", required = false) String role) {

        Page<Ticket> ticketList = null;
        if (!role.contains("EMPLOYEE")&&!role.contains("ADMIN")) {
            ticketList = ticketService.getAllTicketsByCustomerEmail(customerEmail, PageRequest.of(page, 10));
        } else if (role.contains("EMPLOYEE")) {
            ticketList = ticketService.getAllTicketsByEmployeeEmail(customerEmail, PageRequest.of(page, 10));
        }
        if (ticketList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<>(ticketList, HttpStatus.OK);
    }

    //    SonNh lấy danh sách ticket by customer email
    @GetMapping("/listHistoryEmail/{email}")
    public ResponseEntity<Page<Ticket>> listHistoryTicketListByCustomerEmail(@PathVariable(value = "email") String customerEmail,
                                                                             @RequestParam(value = "page", defaultValue = "0") int page,
                                                                             @RequestParam(name = "ROLE", defaultValue = "", required = false) String role) {
        Page<Ticket> ticketList = null;
        if (!role.contains("EMPLOYEE")&&!role.contains("ADMIN")) {
            ticketList = ticketService.getHistoryTicketsByCustomerEmail(customerEmail, PageRequest.of(page, 10));
        } else if (role.contains("EMPLOYEE")) {
            ticketList = ticketService.getHistoryTicketsByEmployeeEmail(customerEmail, PageRequest.of(page, 10));
        }

        if (ticketList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<>(ticketList, HttpStatus.OK);
    }

    //    SonNH search theo ten email, trạng thái vé, ngày bắt đầu bay, người đi.
    @GetMapping(value = "/searchListHistoryEmail/{email}")
    public ResponseEntity<Page<Ticket>> searchlistHistoryTicketListByCustomerEmail(@PathVariable(value = "email") String customerEmail,
                                                                                   @RequestParam(name = "statusTicket", defaultValue = "", required = false) String statusTicket,
                                                                                   @RequestParam(value = "page", defaultValue = "0") int page,
                                                                                   @RequestParam(name = "startFlight", defaultValue = "", required = false) String startFlight,
                                                                                   @RequestParam(name = "buyer", defaultValue = "", required = false) String buyer,
                                                                                   @RequestParam(name = "ROLE", defaultValue = "", required = false) String role) {
        System.out.println("trang thai:" + statusTicket);
        Page<Ticket> ticketList = null;
        System.out.println("status" + statusTicket);
        System.out.println(role);
        Boolean bl = false;

        if (!role.contains("EMPLOYEE")&&!role.contains("ADMIN")) {

            if (statusTicket.equals("false")) {
                ticketList = ticketService.searchHistoryTicketsByCustomerEmail(customerEmail, bl, startFlight, buyer, PageRequest.of(page, 10));
            } else if (statusTicket.equals("true")) {
                bl = true;
                ticketList = ticketService.searchHistoryTicketsByCustomerEmail(customerEmail, bl, startFlight, buyer, PageRequest.of(page, 10));
            } else if (statusTicket.equals("null")) {
                ticketList = ticketService.searchHistoryTicketsByCustomerEmailAbort(customerEmail, startFlight, buyer, PageRequest.of(page, 10));
            } else {
                ticketList = ticketService.searchHistoryTicketsByCustomerEmailNotStatus(customerEmail, startFlight, buyer, PageRequest.of(page, 10));
            }
//            if (ticketList.isEmpty()) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
//            }
//            return new ResponseEntity<>(ticketList, HttpStatus.OK);
        } else if (role.contains("EMPLOYEE")&&!role.contains("ADMIN")) {

            if (statusTicket.equals("false")) {
                ticketList = ticketService.searchHistoryTicketsByEmployeeEmail(customerEmail, bl, startFlight, buyer, PageRequest.of(page, 10));
            } else if (statusTicket.equals("true")) {
                bl = true;
                ticketList = ticketService.searchHistoryTicketsByEmployeeEmail(customerEmail, bl, startFlight, buyer, PageRequest.of(page, 10));
            } else if (statusTicket.equals("null")) {
                ticketList = ticketService.searchHistoryTicketsByEmployeeEmailAbort(customerEmail, startFlight, buyer, PageRequest.of(page, 10));
            } else {
                ticketList = ticketService.searchHistoryTicketsByEmployeeEmailNotStatus(customerEmail, startFlight, buyer, PageRequest.of(page, 10));
            }
//            if (ticketList.isEmpty()) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
//            }
//            return new ResponseEntity<>(ticketList, HttpStatus.OK);
        }
        if (ticketList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<>(ticketList, HttpStatus.OK);

    }
//--------------------------------------------------------
    //    SonNh lấy ticket by CodeTicket
    @GetMapping(value = "/{code_ticket}")
    public ResponseEntity<Ticket> getTicketByID(@PathVariable("code_ticket") String codeTicket) {
        System.out.println("Fetching Ticket with id " + codeTicket);
        Ticket ticket = ticketService.findTicketByCodeTicket(codeTicket);
        if (ticket == null) {
            System.out.println("Ticket with id " + codeTicket + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ticket, HttpStatus.OK);
    }

//        SonNh pay ticket by ticket code
    @PatchMapping(value = "/pay/{code}")
    public ResponseEntity<Ticket> payTicketByCode(@PathVariable("code") String codeTicket) {
        System.out.println("Fetching Ticket with id " + codeTicket);
        Ticket ticket = ticketService.findTicketByCodeTicket(codeTicket);
        if (ticket == null) {
            System.out.println("Ticket with id " + codeTicket + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ticketService.payTicketByCodeTicket(codeTicket);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//        SonNh pay tickets ticket by ticket codes
    @PatchMapping(value = "/pays/{codes}")
    public ResponseEntity<Ticket> payTicketByCodes(@PathVariable("codes") List<String> codeTicketList) {
        System.out.println("Fetching Ticket with id " + codeTicketList);
        Ticket ticket;
        for (int i = 0; i < codeTicketList.size(); i++) {
            ticket = ticketService.findTicketByCodeTicket(codeTicketList.get(i));
            if (ticket == null) {
                System.out.println("Ticket with id " + codeTicketList.get(i) + " not found");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
//            SonNH thanh toán cho từng vé
            ticketService.payTicketByCodeTicket(codeTicketList.get(i));
            totalPriceSendMail+= ticket.getPriceTicket();
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //    SonNh get total price ticket by ticket codes
    @GetMapping(value = "/getPrice/{codes}")
    public ResponseEntity<Double> getTotalPrice(@PathVariable("codes") List<String> codeTicketList) {
        System.out.println("Fetching Ticket with id " + codeTicketList);
        Ticket ticket;
        double totalPrice = 0;
        double finalPrice = 0;
        for (int i = 0; i < codeTicketList.size(); i++) {
            ticket = ticketService.findTicketByCodeTicket(codeTicketList.get(i));
            if (ticket == null) {
                System.out.println("Ticket with id " + codeTicketList.get(i) + " not found");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
//            Lấy giá tổng
            totalPrice += ticket.getPriceTicket();
            System.out.println(ticket.getPriceTicket());
        }
        finalPrice = Math.round(totalPrice / 23000);
        return new ResponseEntity<>(finalPrice, HttpStatus.OK);
    }


    @PatchMapping(value = "/abort/{code}")
    public ResponseEntity<Ticket> abortTicket(@PathVariable("code") String codeTicket) {
        System.out.println("Fetching Ticket with id " + codeTicket);
        Ticket ticket = ticketService.findTicketByCodeTicket(codeTicket);
        if (ticket == null) {
            System.out.println("Ticket with id " + codeTicket + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ticketService.abortTicketByCodeTicket(codeTicket);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PutMapping(value = "/sendmail")
    public ResponseEntity<Ticket> sendMailHtml(
            @RequestParam("finalPrice") Double finalPrice,
            @RequestParam("nameCustomer") String nameCustomer,
            @RequestParam("quantity") int quantity
    ) {
        MimeMessage message = emailSender.createMimeMessage();

        int codePayment = Math.toIntExact((10000 + Math.round((Math.random() * 100000))));
        boolean multipart = true;

        NumberFormat currentLocale = NumberFormat.getInstance();


        String price = currentLocale.format(totalPriceSendMail);


        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = formatter.format(date);
        MimeMessageHelper helper = null;
        try {

            helper = new MimeMessageHelper(message, multipart, "utf-8");
            String htmlMsg = "<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "<head>\n" +
                    "    <title></title>\n" +
                    "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n" +
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                    "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\" />\n" +
                    "    <style type=\"text/css\">\n" +
                    "\n" +
                    "        body, table, td, a { -webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%; }\n" +
                    "        table, td { mso-table-lspace: 0pt; mso-table-rspace: 0pt; }\n" +
                    "        img { -ms-interpolation-mode: bicubic; }\n" +
                    "\n" +
                    "        img { border: 0; height: auto; line-height: 100%; outline: none; text-decoration: none; }\n" +
                    "        table { border-collapse: collapse !important; }\n" +
                    "        body { height: 100% !important; margin: 0 !important; padding: 0 !important; width: 100% !important; }\n" +
                    "\n" +
                    "\n" +
                    "        a[x-apple-data-detectors] {\n" +
                    "            color: inherit !important;\n" +
                    "            text-decoration: none !important;\n" +
                    "            font-size: inherit !important;\n" +
                    "            font-family: inherit !important;\n" +
                    "            font-weight: inherit !important;\n" +
                    "            line-height: inherit !important;\n" +
                    "        }\n" +
                    "\n" +
                    "        @media screen and (max-width: 480px) {\n" +
                    "            .mobile-hide {\n" +
                    "                display: none !important;\n" +
                    "            }\n" +
                    "            .mobile-center {\n" +
                    "                text-align: center !important;\n" +
                    "            }\n" +
                    "        }\n" +
                    "        div[style*=\"margin: 16px 0;\"] { margin: 0 !important; }\n" +
                    "    </style>\n" +
                    "<body style=\"margin: 0 !important; padding: 0 !important; background-color: #eeeeee;\" bgcolor=\"#eeeeee\">\n" +
                    "\n" +
                    "\n" +
                    "<div style=\"display: none; font-size: 1px; color: #fefefe; line-height: 1px; font-family: Open Sans, Helvetica, Arial, sans-serif; max-height: 0px; max-width: 0px; opacity: 0; overflow: hidden;\">\n" +
                    "    For what reason would it be advisable for me to think about business content? That might be little bit risky to have crew member like them.\n" +
                    "</div>\n" +
                    "\n" +
                    "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                    "    <tr>\n" +
                    "        <td align=\"center\" style=\"background-color: #eeeeee;\" bgcolor=\"#eeeeee\">\n" +
                    "\n" +
                    "            <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width:600px;\">\n" +
                    "                <tr>\n" +
                    "                    <td align=\"center\" valign=\"top\" style=\"font-size:0; padding: 35px;\" bgcolor=\"#F44336\">\n" +
                    "\n" +
                    "                        <div style=\"display:inline-block; max-width:50%; min-width:100px; vertical-align:top; width:100%;\">\n" +
                    "                            <table align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width:300px;\">\n" +
                    "                                <tr>\n" +
                    "                                    <td align=\"left\" valign=\"top\" style=\"font-family: Arial; font-size: 36px; font-weight: 800; line-height: 48px;\" class=\"mobile-center\">\n" +
                    "                                        <h1 style=\"font-size: 36px; font-weight: 800; margin: 0; color: #ffffff;\">Xác nhận vé</h1>\n" +
                    "                                    </td>\n" +
                    "                                </tr>\n" +
                    "                            </table>\n" +
                    "                        </div>\n" +
                    "\n" +
                    "                        <div style=\"display:inline-block; max-width:50%; min-width:100px; vertical-align:top; width:100%;\" class=\"mobile-hide\">\n" +
                    "                            <table align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width:300px;\">\n" +
                    "                                <tr>\n" +
                    "                                    <td align=\"right\" valign=\"top\" style=\"font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 48px; font-weight: 400; line-height: 48px;\">\n" +
                    "                                        <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" align=\"right\">\n" +
                    "                                            <tr>\n" +
                    "<!--                                                <td style=\"font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 18px; font-weight: 400;\">-->\n" +
                    "<!--                                                    <p style=\"font-size: 18px; font-weight: 400; margin: 0; color: #ffffff;\"><a href=\"#\" target=\"_blank\" style=\"color: #ffffff; text-decoration: none;\">Flight &nbsp;</a></p>-->\n" +
                    "<!--                                                </td>-->\n" +
                    "                                                <td style=\"font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 18px; font-weight: 400; line-height: 24px;\">\n" +
                    "                                                    <a href=\"#\" target=\"_blank\" style=\"color: #ffffff; text-decoration: none;\"><img src=\"https://cdn.pixabay.com/photo/2013/07/13/12/18/boeing-159589_960_720.png\" width=\"100\" height=\"100\" style=\"display: block; border: 0px;\"/></a>\n" +
                    "                                                </td>\n" +
                    "                                            </tr>\n" +
                    "                                        </table>\n" +
                    "                                    </td>\n" +
                    "                                </tr>\n" +
                    "                            </table>\n" +
                    "                        </div>\n" +
                    "\n" +
                    "                    </td>\n" +
                    "                </tr>\n" +
                    "                <tr>\n" +
                    "                    <td align=\"center\" style=\"padding: 35px 35px 20px 35px; background-color: #ffffff;\" bgcolor=\"#ffffff\">\n" +
                    "                        <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width:600px;\">\n" +
                    "                            <tr>\n" +
                    "                                <td align=\"center\" style=\"font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding-top: 25px;\">\n" +
                    "                                    <img src=\"https://img.icons8.com/carbon-copy/100/000000/checked-checkbox.png\" width=\"125\" height=\"120\" style=\"display: block; border: 0px;\" /><br>\n" +
                    "                                    <h2 style=\"font-size: 30px; font-weight: 700; line-height: 36px; color: #333333; margin: 0;\">\n" +
                    "                                        Cám ơn quý khách đã sử dụng dịch vụ của chúng tôi!\n" +
                    "                                    </h2>\n" +
                    "                                </td>\n" +
                    "                            </tr>\n" +
                    "                            <tr>\n" +
                    "                                <td align=\"left\" style=\"font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding-top: 10px;\">\n" +
                    "                                    <p style=\"font-size: 16px; font-weight: 400; line-height: 24px; color: #777777;\">\n" +
                    "                                        THÔNG TIN ĐƠN HÀNG THANH TOÁN\n" +
                    "                                    </p>\n" +
                    "                                </td>\n" +
                    "                            </tr>\n" +
                    "                            <tr>\n" +
                    "                                <td align=\"left\" style=\"padding-top: 20px;\">\n" +
                    "                                    <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\">\n" +
                    "                                        <tr>\n" +
                    "                                            <td width=\"60%\" align=\"left\" bgcolor=\"#eeeeee\" style=\"font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 800; line-height: 24px; padding: 10px;\">\n" +
                    "                                                Mã đơn hàng đặt vé #\n" +
                    "                                            </td>\n" +
                    "                                            <td width=\"40%\" align=\"left\" bgcolor=\"#eeeeee\" style=\"font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 800; line-height: 24px; padding: 10px;\">\n" +
                    "                                                \n" + codePayment +
                    "                                            </td>\n" +
                    "                                        </tr>\n" +
                    "                                        <tr>\n" +
                    "                                            <td width=\"60%\" align=\"left\" style=\"font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding: 15px 10px 5px 10px;\">\n" +
                    "                                                Thông tin người đặt (1)\n" +
                    "                                            </td>\n" +
                    "                                            <td width=\"40%\" align=\"left\" style=\"font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding: 15px 10px 5px 10px;\">\n" +
                    "                                                \n" + nameCustomer +
                    "                                            </td>\n" +
                    "                                        </tr>\n" +
                    "                                        <tr>\n" +
                    "                                            <td width=\"60%\" align=\"left\" style=\"font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding: 5px 10px;\">\n" +
                    "                                                Số vé đã đặt\n" +
                    "                                            </td>\n" +
                    "                                            <td width=\"40%\" align=\"left\" style=\"font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding: 5px 10px;\">\n" +
                    "                                                \n" + quantity +
                    "                                            </td>\n" +
                    "                                        </tr>\n" +
                    "\n" +
                    "                                    </table>\n" +
                    "                                </td>\n" +
                    "                            </tr>\n" +
                    "                            <tr>\n" +
                    "                                <td align=\"left\" style=\"padding-top: 20px;\">\n" +
                    "                                    <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\">\n" +
                    "                                        <tr>\n" +
                    "                                            <td width=\"60%\" align=\"left\" style=\"font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 800; line-height: 24px; padding: 10px; border-top: 3px solid #eeeeee; border-bottom: 3px solid #eeeeee;\">\n" +
                    "                                                Tổng tiền đã thanh toán\n" +
                    "                                            </td>\n" +
                    "                                            <td width=\"40%\" align=\"left\" style=\"font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 800; line-height: 24px; padding: 10px; border-top: 3px solid #eeeeee; border-bottom: 3px solid #eeeeee;\">\n" +
                    "                                                \n" + price+ " VND" +
                    "                                            </td>\n" +
                    "                                        </tr>\n" +
                    "                                    </table>\n" +
                    "                                </td>\n" +
                    "                            </tr>\n" +
                    "                        </table>\n" +
                    "\n" +
                    "                    </td>\n" +
                    "                </tr>\n" +
                    "                <tr>\n" +
                    "                    <td align=\"center\" height=\"100%\" valign=\"top\" width=\"100%\" style=\"padding: 0 35px 35px 35px; background-color: #ffffff;\" bgcolor=\"#ffffff\">\n" +
                    "                        <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width:660px;\">\n" +
                    "                            <tr>\n" +
                    "                                <td align=\"center\" valign=\"top\" style=\"font-size:0;\">\n" +
                    "                                    <div style=\"display:inline-block; max-width:50%; min-width:240px; vertical-align:top; width:100%;\">\n" +
                    "\n" +
                    "                                        <table align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width:300px;\">\n" +
                    "                                            <tr>\n" +
                    "                                                <td align=\"left\" valign=\"top\" style=\"font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px;\">\n" +
                    "                                                    <p style=\"font-weight: 800;\">Thông tin liên hệ</p>\n" +
                    "                                                    <p>295<br>Tầng 10<br>CodeGym Đà Nẵng</p>\n" +
                    "\n" +
                    "                                                </td>\n" +
                    "                                            </tr>\n" +
                    "                                        </table>\n" +
                    "                                    </div>\n" +
                    "                                    <div style=\"display:inline-block; max-width:50%; min-width:240px; vertical-align:top; width:100%;\">\n" +
                    "                                        <table align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width:300px;\">\n" +
                    "                                            <tr>\n" +
                    "                                                <td align=\"left\" valign=\"top\" style=\"font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px;\">\n" +
                    "                                                    <p style=\"font-weight: 800;\">Ngày Thanh toán</p>\n" +
                    "                                                    <p></p>\n" + strDate +
                    "                                                </td>\n" +
                    "                                            </tr>\n" +
                    "                                        </table>\n" +
                    "                                    </div>\n" +
                    "                                </td>\n" +
                    "                            </tr>\n" +
                    "                        </table>\n" +
                    "                    </td>\n" +
                    "                </tr>\n" +
                    "                <tr>\n" +
                    "                    <td align=\"center\" style=\" padding: 35px; background-color: #ff7361;\" bgcolor=\"#1b9ba3\">\n" +
                    "                        <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width:600px;\">\n" +
                    "                            <tr>\n" +
                    "                                <td align=\"center\" style=\"font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding-top: 25px;\">\n" +
                    "                                    <h2 style=\"font-size: 24px; font-weight: 800; line-height: 30px; color: #ffffff; margin: 0;\">\n" +
                    "                                        Chúc quý khách tận hưởng chuyến bay\n" +
                    "                                    </h2>\n" +
                    "                                </td>\n" +
                    "                            </tr>\n" +
                    "                        </table>\n" +
                    "                    </td>\n" +
                    "                </tr>\n" +
                    "                <tr>\n" +
                    "                    <td align=\"center\" style=\"padding: 35px; background-color: #ffffff;\" bgcolor=\"#ffffff\">\n" +
                    "                        <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width:600px;\">\n" +
                    "                            <tr>\n" +
                    "                                <td align=\"center\">\n" +
                    "                                    <img src=\"https://www.vietnamairlines.com/Themes/VNANew/Portal/images/img-logo-partner-1.png\" width=\"100\" height=\"100\" style=\"display: block; border: 0px;\"/>\n" +
                    "                                </td>\n" +
                    "                            </tr>\n" +
                    "\n" +
                    "                            <tr>\n" +
                    "                                <td align=\"left\" style=\"font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 14px; font-weight: 400; line-height: 24px;\">\n" +
                    "                                    <p style=\"font-size: 14px; font-weight: 400; line-height: 20px; color: #777777;\">\n" +
                    "                                        Nếu bạn không tạo tài khoản bằng địa chỉ email này, vui lòng bỏ qua email này hoặc <a href=\"#\" target=\"_blank\" style=\"color: #777777;\">hủy đăng ký.</a>.\n" +
                    "                                    </p>\n" +
                    "                                </td>\n" +
                    "                            </tr>\n" +
                    "                        </table>\n" +
                    "                    </td>\n" +
                    "                </tr>\n" +
                    "            </table>\n" +
                    "        </td>\n" +
                    "    </tr>\n" +
                    "</table>\n" +
                    "\n" +
                    "</body>\n" +
                    "</html>\n";

            helper.setText("Thành công", htmlMsg);

            helper.setTo(Security_Email.MAIL_RECEIVER);
//            helper.setTo("hungson.star@gmail.com");

            helper.setSubject("Xác nhận thanh toán thành công vé máy bay");

            this.emailSender.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }






    @PostMapping("/getTicketBySeat")
    public ResponseEntity<List<Ticket>> getListTicketBySeat(@RequestBody List<Long> arrValue) {
        List<Ticket> ticketList = new ArrayList<>();
        Ticket ticket;
//        String[] idList = id.split("");
        for (Long item : arrValue) {
            ticket = ticketService.getTicketBySeat(item);

            ticketList.add(ticket);
        }
        if (ticketList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(ticketList, HttpStatus.OK);
    }


//    @GetMapping("/getListSeat")
//    public ResponseEntity<List<SeatTicketDto>> getListSeat(@RequestParam(defaultValue = "") Long idFlight,
//                                                           @RequestParam(defaultValue = "") String typeSeat) {
////        ,
////        @RequestParam(defaultValue = "0") int page
////        , PageRequest.of(page, 5)
//
//        List<SeatTicketDto> seatList = ticketService.getListSeatTicket(idFlight, typeSeat);
////        System.out.println(seatList.get(1));
//        if (seatList.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        } else {
//            return new ResponseEntity<>(seatList, HttpStatus.OK);
//        }
//
//    }
    @ResponseBody
    @PostMapping("/sendEmail")
    public ResponseEntity sendEmailTickets(@RequestBody TicketMailDto ticketMailDto){

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.stmp.user", "ticketairlinedtt3020@gmail.com");

        //To use TLS
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.password", "dnlgrbirymxfchpp");
        //To use SSL
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props, smtpAuthenticator);
        String to = ticketMailDto.getEmailBuyer();
        String from = "ticketairlinedtt3020@gmail.com";
        String subject = "Detail Ticket" ;
        MimeMessage msg = new MimeMessage(session);
        MimeMessageHelper helper = new MimeMessageHelper(msg, "UTF-8");
        try {
            helper.setFrom(new InternetAddress(from));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            helper.setSubject(subject);
            String content =
                    "<table align=\"center\" border=\"1\" cellpadding=\"0\" cellspacing=\"0\" width=\"600\">\n" +
                            "\n" +
                            "    <tr>\n" +
                            "        <td align=\"center\" bgcolor=\"#e7ebef\" style=\"padding: 40px 0 30px 0;\">\n" +
                            "            <img src=\"https://www.maulogo.com/data/001/mau-logo-may-bay-dep-01.jpg\" alt=\"Creating Email Magic\" width=\"300\" height=\"230\" style=\"display: block;\"/>\n" +
                            "        </td>\n" +
                            "    </tr>\n" +
                            "\n" +
                            "    <tr>\n" +
                            "        <td bgcolor=\"#ffffff\" style=\"padding: 40px 30px 40px 30px;\">\n" +
                            "            <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                            "                <tr>\n" +
                            "                    <td>\n" +
                            "                        Xin chào quý khách " + ticketMailDto.getEmailBuyer() + " \n" +
                            "                    </td>\n" +
                            "                </tr>\n" +
                            "                <tr>\n" +
                            "                    <td style=\"padding: 20px 0 30px 0;\">\n" +
                            "                        <p style=\"color: brown\">Hãng hàng không C1021G1Airline chúng tôi thông báo với quý khách,vế việc\n" +
                            "                            khách hàng đã\n" +
                            "                            đăng ký sử dụng dịch vụ của hãng hàng không chúng tôi</p>\n" +
                            "                    </td>\n" +
                            "                </tr>\n" +
                            "                <tr>\n" +
                            "                    <td>\n" +
                            "                        <p>quý khách đă đăng ký thành công " + ticketMailDto.getNumTicket() + "  vé và tổng số tiền là " + ticketMailDto.getSumPrice() + " VND </p>\n" +
                            "                        <p>rất cảm ơn khách hàng đã tin tưởng và sư dụng dịch vụ của chúng tôi,rất mong trong tương lai\n" +
                            "                            rất mong quý khách\n" +
                            "                            vẩn tin tưởng sử dụng dịch vụ của chúng tôi</p>\n" +
                            "                    </td>\n" +
                            "                </tr>\n" +
                            "            </table>\n" +
                            "        </td>\n" +
                            "    </tr>\n" +
                            "    <tr>\n" +
                            "        <td width=\"75%\">\n" +
                            "            &reg; Someone, somewhere 2022<br/>\n" +
                            "            Unsubscribe to this newsletter instantly\n" +
                            "        </td>\n" +
                            "\n" +
                            "\n" +
                            "    </tr>\n" +
                            "</table>" ;
            helper.setText(content, true);
            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com", 465, "ticketairlinedtt3020@gmail.com", "dnlgrbirymxfchpp");
            transport.send(msg);
            System.out.println("Send Email Successfully!!");
        } catch (Exception exc) {
            System.out.println(exc);
        }
        return new ResponseEntity(null, HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping("/sendEmailTicket")
    public ResponseEntity<TicketMailDto> sendEmailTicket(@RequestBody TicketMailDto ticketMailDto) {

        Double money = ticketMailDto.getSumPrice();
        NumberFormat formatter = NumberFormat.getNumberInstance();
        String moneyString = formatter.format(money);
        MimeMessage message = emailSender.createMimeMessage();

        boolean multipart = true;

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, multipart, "utf-8");

            String htmlEmail = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\"\n" +
                    "        \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
                    "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
                    "<head>\n" +
                    "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/>\n" +
                    "    <title>Demystifying Email Design</title>\n" +
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"/>\n" +
                    "\n" +
                    "</head>\n" +
                    "\n" +
                    "<body style=\"margin: 0; padding: 0;\">\n" +
                    "<table align=\"center\" border=\"1\" cellpadding=\"0\" cellspacing=\"0\" width=\"600\">\n" +
                    "\n" +
                    "    <tr>\n" +
                    "        <td align=\"center\" bgcolor=\"#e7ebef\" style=\"padding: 40px 0 30px 0;\">\n" +
                    "            <img src=\"https://www.maulogo.com/data/001/mau-logo-may-bay-dep-01.jpg\" alt=\"Creating Email Magic\" width=\"300\" height=\"230\" style=\"display: block;\"/>\n" +
                    "        </td>\n" +
                    "    </tr>\n" +
                    "\n" +
                    "    <tr>\n" +
                    "        <td bgcolor=\"#ffffff\" style=\"padding: 40px 30px 40px 30px;\">\n" +
                    "            <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                    "                <tr>\n" +
                    "                    <td>\n" +
                    "                        Xin chào quý khách " + ticketMailDto.getEmailBuyer() + " \n" +
                    "                    </td>\n" +
                    "                </tr>\n" +
                    "                <tr>\n" +
                    "                    <td style=\"padding: 20px 0 30px 0;\">\n" +
                    "                        <p style=\"color: brown\">Hãng hàng không C1021G1Airline chúng tôi thông báo với quý khách,vế việc\n" +
                    "                            khách hàng đã\n" +
                    "                            đăng ký sử dụng dịch vụ của hãng hàng không chúng tôi</p>\n" +
                    "                    </td>\n" +
                    "                </tr>\n" +
                    "                <tr>\n" +
                    "                    <td>\n" +
                    "                        <p>quý khách đă đăng ký thành công " + ticketMailDto.getNumTicket() + "  vé và tổng số tiền là " + moneyString + " VND </p>\n" +
                    "                        <p>rất cảm ơn khách hàng đã tin tưởng và sư dụng dịch vụ của chúng tôi,rất mong trong tương lai\n" +
                    "                            rất mong quý khách\n" +
                    "                            vẩn tin tưởng sử dụng dịch vụ của chúng tôi</p>\n" +
                    "                    </td>\n" +
                    "                </tr>\n" +
                    "            </table>\n" +
                    "        </td>\n" +
                    "    </tr>\n" +
                    "    <tr>\n" +
                    "        <td width=\"75%\">\n" +
                    "            &reg; Someone, somewhere 2022<br/>\n" +
                    "            Unsubscribe to this newsletter instantly\n" +
                    "        </td>\n" +
                    "\n" +
                    "\n" +
                    "    </tr>\n" +
                    "</table>\n" +
                    "</body>\n" +
                    "</html>";
//            helper.setText("Thành công", htmlEmail);
            message.setContent(htmlEmail, "text/html; charset=utf-8");
            helper.setTo(MyConstants.FRIEND_EMAIL);
            helper.setSubject("C1021G1Airline thông báo đặt vé thành công");

            this.emailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @GetMapping("/flightTicket/{id}")
    public ResponseEntity<Flight> getFlightById(@PathVariable Long id) {
        Flight flight = ticketService.findFlightById(id);
        if (flight == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(flight, HttpStatus.OK);
        }
    }

    @GetMapping("/seatTypeTicket")
    public ResponseEntity<List<SeatType>> getSeatTypeList() {
        List<SeatType> seatTypeList = ticketService.getAllSeatTypes();
        if (seatTypeList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(seatTypeList, HttpStatus.OK);
        }
    }

    @PatchMapping("/firstUpdate")
    public ResponseEntity<?> updateFirstTicket(@Valid @RequestBody TicketFirstDto ticketFirstDto, BindingResult bindingResult,
                                               @RequestParam(defaultValue = "") Long idFlight,
                                               @RequestParam(defaultValue = "") String typeSeat) {
        if (bindingResult.hasFieldErrors()) {
            Map<String, String> errorMap = new HashMap<>();
            Map<String, Object> response = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> {
                errorMap.put(error.getField(), error.getDefaultMessage());
            });
            response.put("error", errorMap);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        } else {

            if (ticketService.getTicketByFlightIdAndTypeSeatAndTicketId(idFlight, typeSeat, ticketFirstDto.getId()) == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {

                ticketService.updateFirstTicket(ticketFirstDto.getBuyerTicket(),
                        ticketFirstDto.getBirthdayTicket(), ticketFirstDto.getEmailTicket(), ticketFirstDto.getGenderTicket(),
                        ticketFirstDto.getPhoneTicket(), ticketFirstDto.getDateTicket(), ticketFirstDto.getPriceTicket(), ticketFirstDto.getIdCard(),
                        ticketFirstDto.getEmployee(), ticketFirstDto.getCustomer(), ticketFirstDto.getId());


                Ticket ticketHistory = ticketService.getTicketAddHistory(ticketFirstDto.getId());

                ticketService.addTicketHistory(ticketFirstDto.getBirthdayTicket(), ticketFirstDto.getBuyerTicket(),
                        ticketHistory.getCodeTicket(), ticketHistory.getDelFlagTicket(), ticketFirstDto.getEmailTicket(),
                        ticketFirstDto.getGenderTicket(), ticketFirstDto.getPhoneTicket(), ticketHistory.getPointTicket(),
                        ticketFirstDto.getPriceTicket(), false, ticketFirstDto.getCustomer(),
                        ticketFirstDto.getEmployee(), ticketHistory.getSeat().getId(), ticketFirstDto.getDateTicket(), ticketFirstDto.getIdCard());

            }


            return new ResponseEntity<>(ticketFirstDto, HttpStatus.OK);
        }

    }

    @GetMapping("/findTicket")
    public ResponseEntity<Ticket> getFirstTicketById(@RequestParam(defaultValue = "") Long idFlight,
                                                     @RequestParam(defaultValue = "") String typeSeat,
                                                     @RequestParam(defaultValue = "") Long idTicket) {

        Ticket ticket = ticketService.getTicketByFlightIdAndTypeSeatAndTicketId(idFlight, typeSeat, idTicket);

        if (ticket == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(ticket, HttpStatus.OK);
        }
    }


    @GetMapping("/listTicketType")
    public ResponseEntity<List<Ticket>> getListTicketByIdFlight(@RequestParam(defaultValue = "") Long id, @RequestParam(defaultValue = "") String typeSeat) {

        List<Ticket> ticketList = ticketService.getListNumberTicket(id, typeSeat);

        if (ticketList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(ticketList, HttpStatus.OK);
        }
    }




    @GetMapping("/getListSeat")
    public ResponseEntity<List<SeatTicketDto>> getListSeat(@RequestParam(defaultValue = "") Long idFlight,
                                                           @RequestParam(defaultValue = "") String typeSeat) {
//        ,
//        @RequestParam(defaultValue = "0") int page
//        , PageRequest.of(page, 5)

        List<SeatTicketDto> seatList = ticketService.getListSeatTicket(idFlight, typeSeat);
//        System.out.println(seatList.get(1));
        if (seatList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(seatList, HttpStatus.OK);
        }

    }
//


    @PostMapping("/getIdByEmail")
    public ResponseEntity<?> getIdByEmail(@RequestBody TicketIdByEmail ticketIdByEmail) {
//  if (!role.contains("EMPLOYEE")&&!role.contains("ADMIN")) {
//            ticketList = ticketService.getHistoryTicketsByCustomerEmail(customerEmail, PageRequest.of(page, 10));
//        } else if (role.contains("EMPLOYEE")) {
//            ticketList = ticketService.getHistoryTicketsByEmployeeEmail(customerEmail, PageRequest.of(page, 10));
//        }
        if (!ticketIdByEmail.getRole().contains("EMPLOYEE")&&!ticketIdByEmail.getRole().contains("ADMIN")) {
            Long idCustomer = ticketService.getIdCustomerEmailRole(ticketIdByEmail.getEmail());


            if (idCustomer == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(idCustomer, HttpStatus.OK);


        } else if (ticketIdByEmail.getRole().contains("EMPLOYEE")) {
            Long idEmployee = ticketService.getIdEmployeeByEmailRole(ticketIdByEmail.getEmail());
            if (idEmployee == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(idEmployee, HttpStatus.OK);

        }

        return null;
    }
    @RequestMapping(value = "/editTicket/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<?> updateTicket(@Valid @RequestBody EditTicketDto ticket, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors().get(0).getDefaultMessage(), HttpStatus.NOT_FOUND);
        }

        ticketService.editTicket(ticket);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }











    @GetMapping("/not-pagination")
    public ResponseEntity<Page<TicketDto>> getAllTicketNotPagination(@RequestParam(defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page,10);
        Page<TicketDto> ticketDtos = ticketService.getAllTicketDTONotPagination(pageable);
        if (ticketDtos.isEmpty()) {

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(ticketDtos, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Iterable<TicketDto>> getAllListTicket(@RequestParam(defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page,10);

        Page<TicketDto> ticketPage = this.ticketService.findAllTicket(pageable);
        if (ticketPage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ticketPage, HttpStatus.OK);
    }

    @GetMapping("/page/{id}")
    public ResponseEntity<?> findTicketByIdDto(@PathVariable Long id) {
        TicketDto ticketDto = ticketService.findTicketByIdDto(id);
        if (ticketDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ticketDto, HttpStatus.OK);
    }


    @PatchMapping("/delete/{id}")
    public ResponseEntity<TicketDto> deleteTicketById(@PathVariable Long id) {
        TicketDto tickets = ticketService.findTicketByIdDto(id);
        if (tickets == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ticketService.deleteTicketById(id);
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }




    @GetMapping(value = "/findTicket/{id}")
    public ResponseEntity<?> findTicket(@PathVariable Long id) {
        Ticket ticket = ticketService.findTicketById(id);
        if(ticket == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(ticket, HttpStatus.OK);
        }
    }


    @GetMapping("/search")
    public ResponseEntity<Page<TicketDto>> findAllTicket(String buyer, String toFlight, String fromFlight, String code, @RequestParam(defaultValue = "0") int page) {
        Page<TicketDto> ticketDtos = ticketService.findByAllTicket(buyer, toFlight, fromFlight, code, PageRequest.of(page,10));
        System.out.println(ticketDtos.get());
        if (ticketDtos.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ticketDtos, HttpStatus.OK);
    }



}