// pages/newsDetail/newsDetail.js
// const db = wx.cloud.database()
// const newsDb = db.collection("newsList")
var url = getApp().BASE_URL+"/news";
Page({

  /**
   * 页面的初始数据
   */
  data: {
    news:"",
    isAdd: false
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    let id = options._id
    var article = wx.getStorageSync(id+'');
    if (article == "") {
      // newsDb.where({
      //   _id:id
      // }).get({
      //   success: res=>{
      //     this.setData({
      //       news:res.data[0]
      //     })
      //   }
      // })
      var reqTask = wx.request({
        url: url+'/'+id,
        data: {},
        header: {'content-type':'application/json'},
        method: 'GET',
        dataType: 'json',
        responseType: 'text',
        success: (result)=>{
          this.setData({
            news:result.data
          })
        },
        fail: ()=>{},
        complete: ()=>{}
      });
      
    } else {
      // console.log(this.data.isAdd)
      this.setData({
        isAdd:true,
        news:article
      })
      // console.log(this.data.isAdd)
    }
  },
  addFavorite:function() {
    // console.log(this.data.news)
    wx.setStorageSync(this.data.news.newsId+'', this.data.news);
    this.setData({
      isAdd:true
    })
    // console.log(wx.getStorageSync(this.data.news.newsId+''))
  },
  cancelFavorite:function() {
    wx.removeStorageSync(this.data.news.newsid+'');
    this.setData({
      isAdd:false
    })
  },
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})