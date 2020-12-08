wx.cloud.init()
const db = wx.cloud.database()
const hotList = db.collection("hotList")
const subjectList = db.collection("newsSubject")
let app =  getApp();
var baseUrl = app.BASE_URL
const comment = db.collection("comment")
const row = 4
Page({

  /**
   * 页面的初始数据
   */
  data: {
    id:'',
    content:null
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    if (this.data.id == '')
    this.setData({
      id:options.id
    })
    // if (options.op == '0')
    //   hotList.doc(options.id).get({
    //     success:res=>{
    //       this.setData({
    //         title:res.data
    //       })
    //     }
    //   })
    // else
    //   subjectList.doc(options.id).get({
    //     success:res=>{
    //       this.setData({
    //         title:res.data
    //       })
    //     }
    //   })
    var reqTask = wx.request({
      url: baseUrl+'/topics/'+this.data.id,
      data: {},
      header: {'content-type':'application/json'},
      method: 'GET',
      dataType: 'json',
      responseType: 'text',
      success: (result)=>{
        // console.log(result)
        this.setData({
          title:result.data
        })
      },
      fail: ()=>{},
      complete: ()=>{}
    });
    // console.log(this.data)
    // comment.where({
    //   discuss_id:options.id
    // }).limit(row).orderBy("addDay","desc").get({
    //   success:res=>{
    //     // console.log("success")
    //     this.setData({
    //       views:res.data
    //     })
    //   }
    // })
    var reqTask = wx.request({
      url: baseUrl+'/comment/'+this.data.id+'/0/'+row,
      data: {},
      header: {'content-type':'application/json'},
      method: 'GET',
      dataType: 'json',
      responseType: 'text',
      success: (result)=>{
        let data = result.data
        this.setData({
          views: result.data
        })
        // console.log(result.data)
      },
      fail: ()=>{},
      complete: ()=>{}
    });
  },
  //得到具体话题信息
  // getTitle:function(){
  //   // hotList.doc(options.id).get({
  //   //   success:res=>{
  //   //     this.setData({
  //   //       title:res.data
  //   //     })
  //   //     // console.log(this.data.title)
  //   //   }
  //   // })
  //   var reqTask = wx.request({
  //     url: baseUrl+'/'+options.id,
  //     data: {},
  //     header: {'content-type':'application/json'},
  //     method: 'GET',
  //     dataType: 'json',
  //     responseType: 'text',
  //     success: (result)=>{
  //       console.log(result)
  //     },
  //     fail: ()=>{},
  //     complete: ()=>{}
  //   });
  // },
  //上传留言
  submitForm:function(e) {
    // console.log(e.detail.value)
    // return;
    // console.log(this.data.views) 
    let info = app.globalData.info
    // console.log(info)
    if (info == undefined) {
      wx.showToast({
        title: '请先登录',
        image: '../../images/hot/tishi.png',
        duration: 1500,
      });
      this.onLoad(this.data)
      return;
    }
    // let formData = e.detail.value
    // comment.add({
    //   data:{
    //     username:info.nickName,
    //     img:info.src,
    //     content:formData.content,
    //     addDay:new Date(),
    //     now:today,
    //     discuss_id:this.data.title._id,
    //     like:0,
    //     cai:0
    //   },
    //   success:res=>{
    //     wx.showToast({
    //       title: '上传成功'
    //     });
    //   },
    //   fail:e=>{
    //     wx.showToast({
    //       title:'上传失败'
    //     })
    //   }
    // })
    // console.log(this.data.id)
    if (e.detail.value == '') {
      wx.showToast({
        title: '内容不能为空',
        image: '../../images/hot/tishi.png',
        duration: 1500,
      });
      return;
    }
    let randomId = Number(Math.random().toString().substr(3,5) + Date.now()).toString(36);;
    wx.request({
      url: baseUrl+'/comment/',
      data: {
        cid: randomId,
        userOpenId:app.globalData.openid,
        content: e.detail.value.content,
        tid: this.data.id,
        username:info.nickName,
        img: info.src
      },
      header: {'content-type':'application/json'},
      method: 'POST',
      dataType: 'json',
      responseType: 'text',
      success: (result)=>{
        console.log(1)
        wx.request({
          url: baseUrl+'topics/increase/'+this.data.id,
          data: {},
          header: {'content-type':'application/json'},
          method: 'GET',
          dataType: 'json',
          responseType: 'text',
          success: (result)=>{
            console.log(2)
            this.onLoad(this.data)
          },
          fail: ()=>{},
          complete: ()=>{}
        });
      },
      fail: ()=>{},
      complete: ()=>{}
    });
    wx.hideToast();

  },
  //得到留言
  getComment:function(e) {
    var reqTask = wx.request({
      url: baseUrl+'/comment/'+this.data.id+'/0/'+row,
      data: {},
      header: {'content-type':'application/json'},
      method: 'GET',
      dataType: 'json',
      responseType: 'text',
      success: (result)=>{
        let data = result.data
        this.setData({
          views: result.data
        })
        // console.log(result.data)
      },
      fail: ()=>{},
      complete: ()=>{}
    });
  },

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

})