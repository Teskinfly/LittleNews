wx.cloud.init()
const newscn = wx.cloud.database().collection('newsList')
const row = 3
var app =  getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    // ['消息','收藏','历史','客服','关于']
    myInfo:[],
    region:['广东省','揭阳市','惠来县'],
    now:{
      tmp:0,
      cond_txt:'未知',
      cond_code:'999',
      hum:0,
      press:0,
      vis:0,
      wind_dir:0,
      wind_spd:0,
      wind_sr:0

    },
    myData:[
      {id:0,name:'收藏',src:'/images/my/shoucang.png'},
      {id:1,name:'关于',src:'/images/my/about.png'},
    ],
    toList:[
      {url:'../../pages/treasure/treasure' },
      {url:'../../pages/about/about' },
  ]
  },
  /** 
   * 用户信息
   *
  */
   getMyInfo:function(e){
    //  console.log(e.detail.userInfo)
     if(app.globalData.openid == null)
     {
       wx.cloud.callFunction({
         name:'getOpenid',
         complete:res=>{
          //  console.log(res.result.openid)
           app.globalData.openid = res.result.openid
         }
       })
     }
     let info = e.detail.userInfo
     this.setData({
       isLogin:true,
       myInfo:{
         src:info.avatarUrl,
         nickName:info.nickName
       }
     })
     app.globalData.info = this.data.myInfo
   },
  /**
   * 
   * 得到toList 
   */
  toNewsList:function(e){
    let id = e.currentTarget.dataset.item
    // console.log(this.data.toList[id])
    wx.navigateTo({
      url: this.data.toList[id].url,
      success: (result) => {
        console.log("yes")
      },
      fail: () => {
        wx.showToast({
          title: '跳转失败'
        });
          
      },
    });
      
  },
  getWeather:function()
  {
    var that = this;
    wx.request({
      url: 'https://free-api.heweather.com/s6/weather/now',
      data:{
        location:that.data.region[1],
        key:'0f5740f12123497c89791611accfee5d'
      },
      success: function(res) {
        that.setData({now:res.data.HeWeather6[0].now});
      }
    })
  },
  regionChange:function(e)
  {
    this.setData({region:e.detail.value});
    this.getWeather();
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
    wx.request({
      url: 'https://free-api.heweather.com/s6/weather/now',
      data:{
        location:that.data.region[1],
        key:'0f5740f12123497c89791611accfee5d'
      },
      success: function(res) {
        that.setData({now:res.data.HeWeather6[0].now});
      }
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