package com.zian.travelo.utils;

import com.zian.travelo.entity.Booking;

import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class EmailTemplate {
    public static String createBookingMail(Booking booking){
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <style>\n" +
                "        body {\n" +
                "            width: 800px;\n" +
                "            margin: 0 auto;\n" +
                "            font-family: Arial, sans-serif;\n" +
                "        }\n" +
                "\n" +
                "        #table1 {\n" +
                "            width: 100%;\n" +
                "            border-collapse: collapse;\n" +
                "        }\n" +
                "\n" +
                "        #table1 th,\n" +
                "        #table1 td {\n" +
                "            padding: 8px;\n" +
                "            text-align: left;\n" +
                "            border: 1px solid #ddd;\n" +
                "        }\n" +
                "\n" +
                "        #table1 th {\n" +
                "            background-color: #f2f2f2;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "        <tr>\n" +
                "         <td align=\"center\" style=\"padding:0;Margin:0;padding-top:10px;padding-bottom:10px;font-size:0px\"><img src=\"https://fbpatbx.stripocdn.email/content/guids/CABINET_f3fc38cf551f5b08f70308b6252772b8/images/96671618383886503.png\" alt style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\" width=\"100\"></td>\n" +
                "        </tr>\n" +
                "        <tr>\n" +
                "         <td align=\"center\" class=\"es-m-txt-c\" style=\"padding:0;Margin:0;padding-top:15px;padding-bottom:15px\"><h1 style=\"Margin:0;line-height:55px;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;font-size:46px;font-style:normal;font-weight:bold;color:#333333\">Thông tin đơn hàng</h1></td>\n" +
                "        </tr>\n" +
                "      </table>\n" +
                "\n" +
                "    <table id=\"table1\">\n" +
                "        \n" +
                "        <tr>\n" +
                "            <th colspan=\"2\">Thông tin đơn hàng</th>\n" +
                "        </tr>\n" +
                "\n" +
                "        <tr>\n" +
                "            <td><strong>Số người:</strong></td>\n" +
                "            <td>"+booking.getNumberPerson()+"</td>\n" +
                "        </tr>\n" +
                "\n" +
                "        <tr>\n" +
                "            <td><strong>Ngày đặt:</strong></td>\n" +
                "            <td>"+booking.getCreatedAt()+"</td>\n" +
                "        </tr>\n" +
                "\n" +
                "        <tr>\n" +
                "            <td><strong>Trạng thái:</strong></td>\n" +
                "            <td>Đã xác nhận</td>\n" +
                "        </tr>\n" +
                "\n" +
                "        <tr>\n" +
                "            <td><strong>Tổng tiền:</strong></td>\n" +
                "            <td>"+currencyFormat.format(booking.getTotalPrice())+"</td>\n" +
                "        </tr>\n" +
                "\n" +
                "        <tr>\n" +
                "            <th colspan=\"2\">Thông tin khách hàng</th>\n" +
                "        </tr>\n" +
                "\n" +
                "        <tr>\n" +
                "            <td><strong>Tên:</strong></td>\n" +
                "            <td>"+booking.getName()+"</td>\n" +
                "        </tr>\n" +
                "\n" +
                "        <tr>\n" +
                "            <td><strong>Email:</strong></td>\n" +
                "            <td>"+booking.getEmail()+"</td>\n" +
                "        </tr>\n" +
                "\n" +
                "        <tr>\n" +
                "            <td><strong>Điện thoại:</strong></td>\n" +
                "            <td>"+booking.getCustomer().getPhone()+"</td>\n" +
                "        </tr>\n" +
                "\n" +
                "        <tr>\n" +
                "            <td><strong>Địa chỉ:</strong></td>\n" +
                "            <td>"+booking.getCustomer().getAddress()+"</td>\n" +
                "        </tr>\n" +
                "\n" +
                "        <tr>\n" +
                "            <th colspan=\"2\">Thông tin tour</th>\n" +
                "        </tr>\n" +
                "\n" +
                "        <tr>\n" +
                "            <td><strong>Ngày bắt đầu:</strong></td>\n" +
                "            <td>"+formatter.format(booking.getTour().getStartDate())+"</td>\n" +
                "        </tr>\n" +
                "\n" +
                "        <tr>\n" +
                "            <td><strong>Ngày kết thúc:</strong></td>\n" +
                "            <td>"+formatter.format(booking.getTour().getEndDate())+"</td>\n" +
                "        </tr>\n" +
                "\n" +
                "        <tr>\n" +
                "            <td><strong>Giá:</strong></td>\n" +
                "            <td>"+currencyFormat.format(booking.getTour().getPrice())+"</td>\n" +
                "        </tr>\n" +
                "\n" +
                "        <tr>\n" +
                "            <td><strong>Tên tour:</strong></td>\n" +
                "            <td>"+booking.getTour().getTourInfo().getName()+"</td>\n" +
                "        </tr>\n" +
                "\n" +
                "        <tr>\n" +
                "            <td><strong>Mô tả:</strong></td>\n" +
                "            <td>"+booking.getTour().getTourInfo().getDescription()+"</td>\n" +
                "        </tr>\n" +
                "\n" +
//                "        <tr>\n" +
//                "            <td><strong>Lịch trình:</strong></td>\n" +
//                "            <td> "+itinerary+" </td>\n" +
//                "        </tr>\n" +
//                "\n" +
                "        <tr>\n" +
                "            <td><strong>Địa điểm:</strong></td>\n" +
                "            <td>"+booking.getTour().getTourInfo().getLocation().getProvince()+"</td>\n" +
                "        </tr>\n" +
                "         \n" +
                "    </table>\n" +
                "    <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "        <tr>\n" +
                "         <td align=\"center\" valign=\"top\" style=\"padding:0;Margin:0;width:560px\">\n" +
                "          <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:separate;border-spacing:0px;border-radius:5px\">\n" +
                "            <tr>\n" +
                "             <!-- <td align=\"center\" style=\"padding:0;Margin:0;padding-top:10px;padding-bottom:10px\"><span class=\"es-button-border\" style=\"border-style:solid;border-color:#2CB543;background:#5C68E2;border-width:0px;display:inline-block;border-radius:6px;width:auto\"><a href=\"\" class=\"es-button\" target=\"_blank\" style=\"mso-style-priority:100 !important;text-decoration:none;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;color:#FFFFFF;font-size:20px;padding:10px 30px 10px 30px;display:inline-block;background:#5C68E2;border-radius:6px;font-family:arial, 'helvetica neue', helvetica, sans-serif;font-weight:normal;font-style:normal;line-height:24px;width:auto;text-align:center;mso-padding-alt:0;mso-border-alt:10px solid #5C68E2;padding-left:30px;padding-right:30px\">SHOP NOW</a></span></td> -->\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "             <td align=\"left\" style=\"padding:0;Margin:0;padding-bottom:10px;padding-top:20px\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:21px;color:#333333;font-size:14px\">Nếu có bất kỳ thắc mắc nào hãy Email cho chúng tôi &nbsp;<a target=\"_blank\" href=\"\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:underline;color:#5C68E2;font-size:14px\">TQTrong@</a><a target=\"_blank\" href=\"\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:underline;color:#5C68E2;font-size:14px\">gmail.com</a><a target=\"_blank\" href=\"\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:underline;color:#5C68E2;font-size:14px\">.com</a>&nbsp;</td>\n" +
                "            </tr>\n" +
                "          </table></td>\n" +
                "        </tr>\n" +
                "      </table>\n" +
                "      <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-footer\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%;background-color:transparent;background-repeat:repeat;background-position:center top\">\n" +
                "        <tr>\n" +
                "         <td align=\"center\" style=\"padding:0;Margin:0\">\n" +
                "          <table class=\"es-footer-body\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:transparent;width:640px\">\n" +
                "            <tr>\n" +
                "             <td align=\"left\" style=\"Margin:0;padding-top:20px;padding-bottom:20px;padding-left:20px;padding-right:20px\">\n" +
                "              <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                <tr>\n" +
                "                 <td align=\"left\" style=\"padding:0;Margin:0;width:600px\">\n" +
                "                  <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                    <tr>\n" +
                "                     <td align=\"center\" style=\"padding:0;Margin:0;padding-top:15px;padding-bottom:15px;font-size:0\">\n" +
                "                      <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-table-not-adapt es-social\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                        <tr>\n" +
                "                         <td align=\"center\" valign=\"top\" style=\"padding:0;Margin:0;padding-right:40px\"><img title=\"Facebook\" src=\"https://fbpatbx.stripocdn.email/content/assets/img/social-icons/logo-black/facebook-logo-black.png\" alt=\"Fb\" width=\"32\" style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\"></td>\n" +
                "                         <td align=\"center\" valign=\"top\" style=\"padding:0;Margin:0;padding-right:40px\"><img title=\"Twitter\" src=\"https://fbpatbx.stripocdn.email/content/assets/img/social-icons/logo-black/twitter-logo-black.png\" alt=\"Tw\" width=\"32\" style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\"></td>\n" +
                "                         <td align=\"center\" valign=\"top\" style=\"padding:0;Margin:0;padding-right:40px\"><img title=\"Instagram\" src=\"https://fbpatbx.stripocdn.email/content/assets/img/social-icons/logo-black/instagram-logo-black.png\" alt=\"Inst\" width=\"32\" style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\"></td>\n" +
                "                         <td align=\"center\" valign=\"top\" style=\"padding:0;Margin:0\"><img title=\"Youtube\" src=\"https://fbpatbx.stripocdn.email/content/assets/img/social-icons/logo-black/youtube-logo-black.png\" alt=\"Yt\" width=\"32\" style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\"></td>\n" +
                "                        </tr>\n" +
                "                      </table></td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                     <td align=\"center\" style=\"padding:0;Margin:0;padding-bottom:35px\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:18px;color:#333333;font-size:12px\">Style Casual&nbsp;© 2021 Style Casual, Inc. All Rights Reserved.</p><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:18px;color:#333333;font-size:12px\">4562 Hazy Panda Limits, Chair Crossing, Kentucky, US, 607898</p></td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                     <td style=\"padding:0;Margin:0\">\n" +
                "                      <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" class=\"es-menu\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                        <tr class=\"links\">\n" +
                "                         <td align=\"center\" valign=\"top\" width=\"33.33%\" style=\"Margin:0;padding-left:5px;padding-right:5px;padding-top:5px;padding-bottom:5px;border:0\"><a target=\"_blank\" href=\"\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:none;display:block;font-family:arial, 'helvetica neue', helvetica, sans-serif;color:#999999;font-size:12px\">Visit Us </a></td>\n" +
                "                         <td align=\"center\" valign=\"top\" width=\"33.33%\" style=\"Margin:0;padding-left:5px;padding-right:5px;padding-top:5px;padding-bottom:5px;border:0;border-left:1px solid #cccccc\"><a target=\"_blank\" href=\"\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:none;display:block;font-family:arial, 'helvetica neue', helvetica, sans-serif;color:#999999;font-size:12px\">Privacy Policy</a></td>\n" +
                "                         <td align=\"center\" valign=\"top\" width=\"33.33%\" style=\"Margin:0;padding-left:5px;padding-right:5px;padding-top:5px;padding-bottom:5px;border:0;border-left:1px solid #cccccc\"><a target=\"_blank\" href=\"\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:none;display:block;font-family:arial, 'helvetica neue', helvetica, sans-serif;color:#999999;font-size:12px\">Terms of Use</a></td>\n" +
                "                        </tr>\n" +
                "                      </table></td>\n" +
                "                    </tr>\n" +
                "                  </table></td>\n" +
                "                </tr>\n" +
                "              </table></td>\n" +
                "            </tr>\n" +
                "          </table></td>\n" +
                "        </tr>\n" +
                "      </table>\n" +
                "</body>\n" +
                "\n" +
                "</html>";
    }

}
