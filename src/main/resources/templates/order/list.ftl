<html>
<#--<#include "../common/header.ftl">-->
<head>
    <meta charset="utf-8">
    <title>卖家商品列表</title>
    <link href="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <table class="table">
                <thead>

                <tr>
                <th>订单id</th>
                    <th>姓名</th>
                <th>手机号</th>
                    <th>地址</th>

                    <th>金额</th>
                    <th>订单状态</th>
                    <th>支付状态</th>
                    <th>创建时间</th>

                    <th colspan="2">操作</th>
                </tr>
    </thead>
                <tbody>
                <#list orderDTOPage.content as orderDTO>
                <tr>
                <td>${orderDTO.orderId}</td>
                <td>${orderDTO.buyerName}</td>
                <td>${orderDTO.buyerPhone}</td>
                <td>${orderDTO.buyerAddress}</td>
                <td>${orderDTO.orderAmount}</td>
                <td>${orderDTO.getOrderStatusEnum().getMessage()}</td>
                <td>${orderDTO.getPayStatusEnum().getMessage()}</td>
                    <td>详情</td>
                    <td>取消</td>
                </tr>
                <tr class="success">
                    <td>
                        1
                    </td>
                    <td>
                        TB - Monthly
                    </td>
                    <td>
                        01/04/2012
                    </td>
                    <td>
                        Approved
                    </td>
                </tr>
      </#list>

                </tbody>
            </table>

        </div>
        <div class="col-md-12 column">
            <ul class="pagination pull-right" >
                <#if currentPage lte 1>
                    <li class="disabled">
                        <a href="#">上一页</a>
                    </li>
                <#else><li>
                    <a href="http://127.0.0.1:8080/sell/seller/order/list?page=#{currentPage-1}&size=#{size}"
                </li>
                </#if>


                 <#list 1..orderDTOPage.getTotalPages() as index>
                 <#if currentPage == index>
                 <li class="disabled"><a href="">#{index}</a> </li>
                 <#else>
                  <li><a href="http://127.0.0.1:8080/sell/seller/order/list?page=#{page}&size=#{size}"></a>#{index}</li>

                 </#if>

                 </#list>
                 <#if currentPage lte orderDTOPage.getTotalPages()>
                    <li class="disabled">
                        <a href="#">下一页</a>
                    </li>
                 <#else><li>
                     <a href="http://127.0.0.1:8080/sell/seller/order/list?page=#{currentPage+1}&size=#{size}"
                 </li>
                 </#if>
            </ul>
    </div>

</div>
<#--<div id="wrapper" class="toggled">-->

    <#--&lt;#&ndash;边栏sidebar&ndash;&gt;-->
    <#--<#include "../common/nav.ftl">-->

    <#--&lt;#&ndash;主要内容content&ndash;&gt;-->
    <#--<div id="page-content-wrapper">-->
        <#--<div class="container-fluid">-->
            <#--<div class="row clearfix">-->
                <#--<div class="col-md-12 column">-->
                    <#--<table class="table table-bordered table-condensed">-->
                        <#--<thead>-->
                        <#--<tr>-->
                            <#--<th>订单id</th>-->
                            <#--<th>姓名</th>-->
                            <#--<th>手机号</th>-->
                            <#--<th>地址</th>-->
                            <#--<th>金额</th>-->
                            <#--<th>订单状态</th>-->
                            <#--<th>支付状态</th>-->
                            <#--<th>创建时间</th>-->
                            <#--<th colspan="2">操作</th>-->
                        <#--</tr>-->
                        <#--</thead>-->
                        <#--<tbody>-->

                        <#--<#list orderDTOPage.content as orderDTO>-->
                        <#--<tr>-->
                            <#--<td>${orderDTO.orderId}</td>-->
                            <#--<td>${orderDTO.buyerName}</td>-->
                            <#--<td>${orderDTO.buyerPhone}</td>-->
                            <#--<td>${orderDTO.buyerAddress}</td>-->
                            <#--<td>${orderDTO.orderAmount}</td>-->
                            <#--<td>${orderDTO.getOrderStatusEnum().message}</td>-->
                            <#--<td>${orderDTO.getPayStatusEnum().message}</td>-->
                            <#--<td>${orderDTO.createTime}</td>-->
                            <#--<td><a href="/sell/seller/order/detail?orderId=${orderDTO.orderId}">详情</a></td>-->
                            <#--<td>-->
                                <#--<#if orderDTO.getOrderStatusEnum().message == "新订单">-->
                                    <#--<a href="/sell/seller/order/cancel?orderId=${orderDTO.orderId}">取消</a>-->
                                <#--</#if>-->
                            <#--</td>-->
                        <#--</tr>-->
                        <#--</#list>-->
                        <#--</tbody>-->
                    </table>
                </div>


</body>
<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</html>