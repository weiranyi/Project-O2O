/**
 * 1、后台获取店铺分类、区域信息
 * 2、获取表单信息给后台
 */
$(function () {
    var initUrl = '/O2O/shopadmin/getshopinitinfo'; //获取店铺的初始信息，还未定义
    var registerShopUrl = '/O2O/shopadmin/registershop'; //注册店铺

    getShopInitInfo(); //调用方法
    // 定义第一个方法getShopInitInfo：获取商铺分类、区域的列表信息
    function getShopInitInfo() {
        alert(initUrl); //调试弹窗，证明js文件被加载
        $.getJSON(initUrl, function (data) { //访问的URL，回调方法
            if (data.success) { //true
                var tempHtml = '';//存放店铺类别列表
                var tempAreaHtml = '';//存放区域列表
                data.shopCategoryList.map(function (item, index) {
                    //用map遍历店铺类别列表，生成如<option data-id="1">盖浇饭</option>的列表
                    tempHtml += '<option data-id="' + item.shopCategoryId + '">' + item.shopCategoryName + "</option>";
                });
                data.areaList.map(function (item, index) {
                    //用map遍历区域列表，生成如<option data-id="1">东苑</option>的列表
                    tempAreaHtml += '<option data-id="' + item.areaId + '">' + item.areaName + '</option>';
                });
                //完成遍历之后，将获取到的信息，加入到前台定义好的id里面 #是css的用法，代表id
                $('#shop-category').html(tempHtml);
                $('#area').html(tempAreaHtml);
            }
        });
        //第二个方法：点击提交，获取到表单信息，通过ajax转发到后台
        $('#submit').click(function () {//点击id为submit的空间，调用方法
            var shop = {};//json对象
            //获取控件的信息
            shop.shopName = $('#shop-name').val();
            shop.shopAddr = $('#shop-addr').val();
            shop.phone = $('#shop-phone').val();
            shop.shopDesc = $('#shop-desc').val();
            shop.shopCategory = {
                shopCategoryId: $('#shop-category').find('option').not(function () {
                    //返回被选中的
                    return !this.selected;
                }).data('id')
            };
            shop.area = {
                areaId: $('#area').find('option').not(function () {
                    return !this.selected;
                }).data('id')
            };
            var shopImg = $('#shop-img')[0].files[0];
            var formData = new FormData(); //定义表单，用于接收
            formData.append('shopImg', shopImg);
            formData.append('shopStr', JSON.stringify(shop)); //将json转换成字符流
            $.ajax({
                url: registerShopUrl,
                type: 'POST',
                data: formData,
                contentType: false,//既要传文件，又要传文字，设为false
                processData: false,
                cache: false,
                success: function (data) {
                    if (data.success) {
                        $.toast("提交成功！");
                    } else {
                        $.toast("提交失败：" + data.errMsg);
                    }
                }
            });
        });
    }
})