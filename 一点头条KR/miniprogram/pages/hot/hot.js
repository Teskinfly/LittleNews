wx.cloud.init()
const db = wx.cloud.database()
const hotSubject = db.collection('hotList')
const subject = db.collection('newsSubject')
var page = 0
const row = 10
let app =  getApp();
var baseUrl = app.BASE_URL+'/topics'
  
Page({

  /**
   * 页面的初始数据
   */
  data: {
    currentIndex:0,
    menus:[
     "今日热议",
      "我的讨论",
      "最新讨论"
    ]
  },
  //跳转
  goToDiscuss:function(e){
    // console.log(e.currentTarget.dataset.item)
    wx.navigateTo(
      {
        // url: "../discussDetail/discussDetail?id="+e.currentTarget.dataset.item+"&op="+e.currentTarget.dataset.op
        url: "../discussDetail/discussDetail?id="+e.currentTarget.dataset.item
      }
    )
  },
  //新建话题
  navToCreate:function(e){
    let info = app.globalData.info
    // console.log(info)
    if (info == undefined) {
      wx.showToast({
        title: '请先登录',
        image: '../../images/hot/tishi.png',
        duration: 1500,
      });
      return;
    }
    wx.navigateTo({
      url: '../../pages/newDiscuss/newDiscuss',
    });
      
      
  },
  //删除
  delSubject:function(e){
    let list = this.data.list
    let target = list[e.currentTarget.dataset.item]

    var reqTask = wx.request({
      url: baseUrl+'/'+target.tid,
      data: {},
      header: {'content-type':'application/json'},
      method: 'DELETE',
      dataType: 'json',
      responseType: 'text',
      success: (result)=>{
        console.log(result)
      },
      fail: ()=>{},
      complete: ()=>{}
    });
    let mylist = this.data.list
    if(mylist == undefined) return;
    mylist.splice(e.currentTarget.dataset.item,1)//
    this.setData({
      list:mylist
    })
  },
  //点击Menu触发
  getItems:function(e){
    let id = e.currentTarget.dataset.item;
    this.setData({
      currentIndex:id
    })
    // console.log(id)
    wx.showLoading({
      title: "加载中",
    });
    if(id == 0)
    {
      // hotSubject.limit(row).get({
      //   success:res=>{
      //     this.setData({
      //       list:res.data  
      //     })
      //   }
      // })
      var reqTask = wx.request({
        url: baseUrl+'/level/0/10',
        data: {},
        header: {'content-type':'application/json'},
        method: 'GET',
        dataType: 'json',
        responseType: 'text',
        success: (result)=>{
          this.setData({
            list: result.data
          })
        },
        fail: ()=>{},
        complete: ()=>{}
      });
    }
    else if(id == 1)
    {
      // subject.count({
      //   success:res=>{
      //     this.setData({
      //       cnt:res.total
      //     })
      //   }
      // })
      // subject.where({
      //   _openid:app.globalData.openid
      // }).limit(row).get({
      //   success:res=>{
      //     this.setData({
      //       list:res.data
      //     })
      //   }
      // })
      // console.log(app.globalData.openid)
      wx.request({
        url: baseUrl+'/total/'+app.globalData.openid,
        data: {},
        header: {'content-type':'application/json'},
        method: 'GET',
        dataType: 'json',
        responseType: 'text',
        success: (result)=>{
          this.setData({
            cnt: result.data
          })
        }
      });
      var reqTask = wx.request({
        url: baseUrl+'/openid/'+app.globalData.openid,
        data: {},
        header: {'content-type':'application/json'},
        method: 'GET',
        dataType: 'json',
        responseType: 'text',
        success: (result)=>{
          this.setData({
            list: result.data
          })
        },
        fail: ()=>{},
        complete: ()=>{}
      });
    }
    else if(id == 2){
      // subject.where({
      //   _openid:app.globalData.openid
      // }).limit(row).orderBy("addDay","desc").get({
      //   success:res=>{
      //     // console.log(res.data)
      //     this.setData({
      //       list:res.data
      //     })
      //   }
      // })
      var reqTask = wx.request({
        url: baseUrl+'/date/0/10',
        data: {},
        header: {'content-type':'application/json'},
        method: 'GET',
        dataType: 'json',
        responseType: 'text',
        success: (result)=>{
          this.setData({
            list: result.data
          })
          // console.log(result.data)
        },
        fail: ()=>{},
        complete: ()=>{}
      });
    }
    wx.hideLoading();
  },
  /**
   * 生命周期函数--监听页面加载
   */

  onLoad: function (options) {
    // let id = options.id
    // console.log(id)
    // if(id != null)
    // {
    //   this.setData({
    //     currentIndex:id
    //   })
    //   // console.log(id)
      wx.showLoading({
        title: "加载中",
      });
    //   if(id == 0)
    //   {
    //     // hotSubject.limit(row).get({
    //     //   success:res=>{
    //     //     this.setData({
    //     //       list:res.data  
    //     //     })
    //     //   }
    //     // })
    //     var reqTask = wx.request({
    //       url: baseUrl+'/level/0/10',
    //       data: {},
    //       header: {'content-type':'application/json'},
    //       method: 'GET',
    //       dataType: 'json',
    //       responseType: 'text',
    //       success: (result)=>{
    //         console.log(result)
    //       },
    //       fail: ()=>{},
    //       complete: ()=>{}
    //     });
    //   }
    //   else if(id == 1)
    //   {
    //     subject.count({
    //       success:res=>{
    //         this.setData({
    //           cnt:res.total
    //         })
    //       }
    //     })
    //     subject.where({
    //       _openid:app.globalData.openid
    //     }).limit(row).get({
    //       success:res=>{
    //         this.setData({
    //           list:res.data
    //         })
    //       }
    //     })
    //   }
    //   else if(id == 2){
    //     subject.where({
    //       _openid:app.globalData.openid
    //     }).limit(row).orderBy("addDay","desc").get({
    //       success:res=>{
    //         // console.log(res.data)
    //         this.setData({
    //           list:res.data
    //         })
    //       }
    //     })
    //   }
      wx.hideLoading();
    // }
    // else
    // {
    //   hotSubject.limit(row).get({
    //     success:res=>{
    //       this.setData({
    //         list:res.data
    //       })
    //     }
    //   })
    // }
    // console.log(this.hotList)
    var reqTask = wx.request({
      url: baseUrl+'/level/0/'+row,
      data: {},
      header: {'content-type':'application/json'},
      method: 'GET',
      dataType: 'json',
      responseType: 'text',
      success: (result)=>{
        // console.log(result.data)
        this.setData({
          list:result.data
        })
      }
    });
  },
  search:function(e){
    // console.log(e)
    var reqTask = wx.request({
      url: baseUrl+'/search/'+e.detail.value.keyword,
      data: {},
      header: {'content-type':'application/json'},
      method: 'GET',
      dataType: 'json',
      responseType: 'text',
      success: (result)=>{
        console.log(result)
        this.setData({
          list:result.data
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

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  },
  generateId:function(){
    return Number(Math.random().toString().substr(3,length) + Date.now()).toString(36);
  }
})