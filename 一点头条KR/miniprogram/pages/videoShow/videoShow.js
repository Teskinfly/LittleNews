const db = wx.cloud.database()
const menu = db.collection('newsMenu')
const newsVideo = db.collection('newsVideo')
var page = 0
const row = 3
Page({

  /**
   * 页面的初始数据
   */
  data: {
    currentVideo:-1,
    videos:'',
    currentIndex:0,
  },
  /**
   * 
   * getList
   */
  playVideo:function(e){
    let id = e.currentTarget.dataset.item
    let vctx = wx.createVideoContext('myVideo'+id) 
    if(this.data.currentVideo== -1){
      this.setData({
        vctx:vctx,
        currentVideo:id
      })
    }
    else if(id != this.data.currentVideo){
      this.data.vctx.stop()
      this.setData({
        vctx:vctx,
        currentVideo:id
      })
    } else {

    }
  },
  getNewsList:function(){
    let id = this.data.currentIndex
    newsVideo.where({
      menuId:id
    })
    .limit(row).get({
      success:res=>{
        this.setData({
          videos:res.data
        })
      }
    })
    
  },
  /**
   * choose
   *  
   */
  choose:function(e){
    let id = e.currentTarget.dataset.item
    if(id == this.data.currentIndex) return;
    /**
     * here 
     */
    this.setData({
      currentIndex:id,
      currentVideo:-1
    })
    this.getNewsList()
    page = 0
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    menu.orderBy('menuId','asc').get({
      success:res=>{
        this.setData({
          menuList:res.data
        })
      }
    })
    newsVideo.where({
      menuId:0
    }).limit(row).get({
      success:res=>{
        this.setData({
          videos:res.data
        })
        page = 0
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
    wx.showLoading({
      title: '正在刷新',
    });
    page++;
    newsVideo.skip(row*page).where(
      {menuId:this.data.currentIndex}
    ).limit(row).get({
      success:res=>{
        let newVideo = res.data
        this.setData({
          videos:this.data.videos.concat(newVideo)
        })
      }
    })
    wx.hideLoading();
      
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})