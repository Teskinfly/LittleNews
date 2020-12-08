// pages/treasure/treasure.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    newsList:[]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    let storageInfo = wx.getStorageInfoSync();
    // console.log(storageInfo)
    let keys = storageInfo.keys
    // console.log(keys)
    var favoriteList = []
    for (var i = 0; i < keys.length; i++) {
      favoriteList.push(wx.getStorageSync(keys[i]))
    }
    this.setData({
      newsList:favoriteList
    })
    console.log(this.data.newsList)
  },
  goToDetail:function(e) {
    wx.navigateTo({
      url: '../newsDetail/newsDetail?_id='+e.currentTarget.dataset.item
    });
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