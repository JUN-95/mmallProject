$(function(){
    //计算总价
    var array = $(".qprice");
    var totalCost = 0;
    for(var i = 0;i < array.length;i++){
        var val = parseInt($(".qprice").eq(i).html().substring(1));
        totalCost += val;
    }
    $("#totalprice").html("￥"+totalCost);
    //settlement2使用
    $("#settlement2_totalCost").val(totalCost);
});

//商品数量++
function addQuantity(obj){
    var index = $(".car_btn_2").index(obj);
    var stock = parseInt($(".productStock").eq(index).val());
    var price = parseInt($(".productPrice").eq(index).val());
    var inputObj = $(".car_ipt").eq(index);
    var quantity = inputObj.val();
    var productId = $(".productId").eq(index).val();
    var id = $(".id").eq(index).val();
    ++quantity;
    if(quantity > stock){
        quantity = stock;
        alert("库存不足！");
    }
    var cost = quantity*price;
    $.ajax({
        url:"/cart/updateCart/add/"+id+"/"+quantity+"/"+cost+"/"+productId,
        type:"POST",
        dataType:"text",
        success:function (data) {
            if(data == "success"){
                //更新toSettlement的数据
                $(".qprice").eq(index).html("￥"+cost);
                inputObj.val(quantity);
                if(quantity < stock){
                    var totalCost = parseInt($("#totalprice").html().substring(1));
                    totalCost += price;
                    $("#totalprice").html("￥"+totalCost);
                }
                //更新searchBar的数据
                $(".quantity").eq(index).text(quantity);
                $(".cost").eq(index).text(cost);

                var array = $(".cost");
                var totalCost = 0;
                for(var i = 0;i < array.length;i++){
                    var val = parseInt($(".cost").eq(i).html());
                    totalCost += val;
                }
                $("#totalCost").html("￥"+totalCost);
            }
        }
    });
}

//购物车商品数量进行更改时，先从前端进行数据操作，然后保证数据库中的值先修改，再返回给前端页面的数据进行修改
function subQuantity(obj){
    var index = $(".car_btn_1").index(obj);
    var price = parseInt($(".productPrice").eq(index).val());
    var inputObj = $(".car_ipt").eq(index);
    var quantity = inputObj.val();
    var id = $(".id").eq(index).val();   // 购物车一条信息的id
    --quantity;
    if(quantity == 0){
        quantity = 1;
    }
    var cost = quantity*price;
    var productId = $(".productId").eq(index).val();

//购物车商品数量进行更改时，先从前端进行数据操作，然后保证数据库中的值先修改，再返回给前端页面的数据进行修改
    $.ajax({
        url:"/cart/updateCart/sub/"+id+"/"+quantity+"/"+cost+"/"+productId,
        type:"POST",
        dataType:"text",
        success:function(data){     // 回调函数， 上面url进行post请求后，请求返回的值在传入到函数中去
            if(data == "success"){    // 回调函数， 上面url进行post请求成功，controller返回"success"
                $(".qprice").eq(index).html("￥"+cost);
                inputObj.val(quantity);
                if(quantity!=1){
                    var totalCost = parseInt($("#totalprice").html().substring(1));
                    totalCost -= price;
                    $("#totalprice").html("￥"+totalCost);
                }
                $(".quantity").eq(index).text(quantity);
                $(".cost").eq(index).text(cost);

                var array = $(".cost");
                var totalCost = 0;
                for(var i = 0;i < array.length;i++){
                    var val = parseInt($(".cost").eq(i).html());
                    totalCost += val;
                }
                $("#totalCost").html("￥"+totalCost);
            }
        }
    });
}

//移出购物车
function removeCart(obj){
    var index = $(".delete").index(obj);
    var id = $(".id").eq(index).val();
    if(confirm("是否确定要删除？")){
        window.location.href = "/cart/removeCart/"+id;
    }
}