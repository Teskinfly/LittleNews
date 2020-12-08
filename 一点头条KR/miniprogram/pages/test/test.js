// pages/test/test.js
var app =  getApp();
var baseUrl = app.BASE_URL+'/hi'
Page({

  /**
   * 页面的初始数据
   */
  data: {

  },
  getMessage:function() {
    var reqTask = wx.request({
      url: baseUrl+'/message',
      data: {},
      header: {'content-type':'application/json'},
      method: 'GET',
      dataType: 'json',
      responseType: 'text',
      success: (result)=>{
        console.log(result)
      },
      fail: ()=>{},
      complete: ()=>{}
    });
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  }
})