<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    </head>

    <body>
        <style>
            body{
  background: #C0C0C0;
}
        </style>
        <jsp:include page="Menu.jsp"></jsp:include>
            <div class="shopping-cart">
                <div class="px-4 px-lg-0">

                    <div class="pb-5">
                        <div class="container">
                            <div class="row">
                                <div class="col-lg-12 p-5 bg-white rounded shadow-sm mb-5">

                                    <!-- Shopping cart table -->
                                    <div class="table-responsive">
                                        <table class="table">
                                            <thead>
                                                <tr>
                                                    <th scope="col" class="border-0 bg-light">
                                                        <div class="p-2 px-3 text-uppercase">Product Name</div>
                                                    </th>
                                                    <th scope="col" class="border-0 bg-light">
                                                        <div class="py-2 text-uppercase">Price</div>
                                                    </th>
                                                    <th scope="col" class="border-0 bg-light">
                                                        <div class="py-2 text-uppercase">Quantity</div>
                                                    </th>
                                                    <th scope="col" class="border-0 bg-light">
                                                        <div class="py-2 text-uppercase">Delete</div>
                                                    </th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach items="${sessionScope.CART}" var="o">
                                                <tr>
                                                    <th scope="row">
                                                        <div class="p-2">
                                                            <img src="${o.book.getImage()}" alt="" width="70" class="img-fluid rounded shadow-sm">
                                                            <div class="ml-3 d-inline-block align-middle">
                                                                <h5 class="mb-0">${o.book.getBanhName()}</h5><span class="text-muted font-weight-normal font-italic"></span>
                                                            </div>
                                                        </div>
                                                    </th>
                                                    <td class="align-middle"><strong>${o.getPrice()}</strong></td>
                                                    <td class="align-middle">
                                                        <strong>${o.getQuantity()}</strong>
                                                    </td>
                                                    <td class="align-middle">
                                                        <a href="MainController?action=deletecart&did=${o.book.getBanhID()}" class="text-dark">
                                                            <button type="button" value="deletecart" class="btn btn-danger">Delete</button>
                                                        </a>
                                                    </td>
                                                </tr>


                                                <c:set var="total" value="${total+o.getPrice()}"/>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="row py-5 p-4 bg-white rounded shadow-sm">
                            <div class="col-lg-6">                                
                                <div class="p-4">
                                    <div class="input-group mb-4 border rounded-pill p-2">
                                        
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="bg-light rounded-pill px-4 py-3 text-uppercase font-weight-bold">Amount </div>
                                <div class="p-4">
                                    <ul class="list-unstyled mb-4">
                                      
                                        <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">Sub Total</strong><strong> ${total}</strong></li>
                                        <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">Ship</strong><strong>Free ship</strong></li>
                                        <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">TAX</strong><strong>0,5 $</strong></li>
                                        <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">Total Amount</strong>
                                            <h5 class="font-weight-bold">${total+0.5} $
                                            </h5>
                                        </li>
                                    </ul><a href="MainController?action=CheckOut&total=${total+0.5}&userID=${sessionScope.USER.getUserID()}&voucherID=${requestScope.DISCOUNT.getDiscountID()}" class="btn btn-dark rounded-pill py-2 btn-block">Checkout</a>
                                </div>
                                <div id="paypal-button-container"></div>
                                <form action="MainController" method="POST" style="display: none">
                                    <input type="hidden" name="total" value="${total+0.5}">
                                    <input type="hidden" name="userID" value="${sessionScope.USER.getUserID()}">
<!--                                    <input type="hidden" name="voucherID" value="${requestScope.DISCOUNT.getDiscountID()}">-->
                                    <input type="submit" name="action" id="ppButton" value="Paypal">
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <script src="https://www.paypal.com/sdk/js?client-id=Abt1sXyO3BlIRE3m7690odmoURU0BYNR9JeEkTXZtGQX2bX6jFt4BHB7bOFmcZ1zTGt0VvVDhub8nqsk"></script>
        <script>
            
                paypal.Buttons({
                    
                    style: {
                        color: 'blue',
                        shape: 'pill'
                    },
                    createOrder: function (data, actions) {
                        //H??m n??y thi???t l???p chi ti???t c???a giao d???ch, bao g???m s??? ti???n v?? chi ti???t m???c h??ng. 
                        return actions.order.create({
                            purchase_units: [{
                                    amount: {
                                        value: '${total+0.5}'
                                    }
                                }]
                        });
                    },
                    onApprove: function (data, actions) {
                        // Ch???c n??ng n??y thu ti???n t??? giao d???ch.
                        return actions.order.capture().then(function (details) {
                            // Ch???c n??ng n??y hi???n th??? th??ng b??o giao d???ch th??nh c??ng cho ng?????i mua c???a b???n.
                            document.getElementById("ppButton").click();
                        });
                    }
                }).render('#paypal-button-container');
                //Ch???c n??ng n??y hi???n th??? c??c N??t thanh to??n th??ng minh tr??n trang web c???a b???n.  


        </script>

    </body>

</html>
</html>
