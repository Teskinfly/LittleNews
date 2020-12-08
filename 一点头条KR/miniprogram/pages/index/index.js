wx.cloud.init()
const db = wx.cloud.database()
const swiper = db.collection('newsSwiper')
// const menu = db.collection('newsMenu')
var appInst =  getApp();
var menuUrl = appInst.BASE_URL + "/menu"
var newsUrl = appInst.BASE_URL + "/news"
// const newscn = db.collection('newsList')
const row = 5
var page = 0
Page({

  /**
   * 页面的初始数据
   */
  data: {
    swiperList:[],
    menuList:[],
    newsList:[],
    currentIndex:0,
  },
  //点击目录触发
  choose:function(e){
    let id = e.currentTarget.dataset.item
    if(id == this.data.currentIndex) return;
    /**
     * here 
     */
    this.setData({
      currentIndex:id
    })
    this.getNewsList()
  },
  //加载数据
  getNewsList:function(){
    // let id = this.data.currentIndex
    // newscn.where({
    //   menuId:id
    // }).limit(row).get({
    //   success:res=>{
    //     this.setData({
    //       newsList:res.data
    //     })
    //     page = 0     
    //   }
    // })
    let id = this.data.currentIndex
    var reqTask = wx.request({
      url: newsUrl+'/'+id+'/'+'/0/'+row,
      data: {},
      header: {'content-type':'application/json'},
      method: 'GET',
      dataType: 'json',
      responseType: 'text',
      success: (result)=>{
        this.setData({
          newsList:result.data
        })
      },
      fail: ()=>{},
      complete: ()=>{}
    });
  },
  //
  goToDetail:function(e) {
    wx.navigateTo({
      url: '../newsDetail/newsDetail?_id='+e.currentTarget.dataset.item,
    });
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    swiper.orderBy('swiperId','asc').get({
      success:res=>{
        this.setData({
          swiperList:res.data
        })
      }
    })
      // menu.orderBy('menuId','asc').get({
      //   success:res=>{
      //     this.setData({
      //       menuList:res.data
      //     })
      //   }
      // })
      // newscn.where({
      //   menuId:0
      // }).limit(row).get({
      //   success:res=>{
      //     this.setData({
      //       newsList:res.data
      //     })
      //     page = 0     
      //   }
      // })
      var reqTask = wx.request({
        url: menuUrl,
        header: {'content-type':'application/json'},
        method: 'GET',
        dataType: 'json',
        responseType: 'text',
        success: (result)=>{
          console.log(result)
          this.setData({
            menuList:result.data
          })
        }
      });
      var reqTask = wx.request({
        url: newsUrl+'/0/0/'+row,
        data: {},
        header: {'content-type':'application/json'},
        method: 'GET',
        dataType: 'json',
        responseType: 'text',
        success: (result)=>{
          console.log(result.data)
          this.setData({
            newsList:result.data
          })
        },
        fail: ()=>{},
        complete: ()=>{}
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
    wx.showLoading({
      title: '正在刷新',
    });
    page++
    let id = this.data.currentIndex
    // newscn.skip(row*page).where({
    //   menuId:id
    // }).limit(row).get({
    //   success:res=>{
    //     let old_data = this.data.newsList
    //     let new_data = res.data
    //     this.setData({
    //       newsList:old_data.concat(new_data)
    //     })
    //   }
    // })
    var reqTask = wx.request({
      url: newsUrl+'/'+id+'/'+page*row+"/"+row,
      data: {},
      header: {'content-type':'application/json'},
      method: 'GET',
      dataType: 'json',
      responseType: 'text',
      success: (res)=>{
        let old_data = this.data.newsList
        let new_data = res.data
        this.setData({
          newsList:old_data.concat(new_data)
        })
      },
      fail: ()=>{},
      complete: ()=>{}
    });
    wx.hideLoading();
      
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
    
  }
})