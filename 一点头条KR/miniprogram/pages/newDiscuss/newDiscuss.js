
const db = wx.cloud.database()
const subject = db.collection('newsSubject')
let app =  getApp();
var baseUrl = app.BASE_URL+'/topics'
// function formatDate(){
//   var now = new Date()
//   var year = now.getFullYear()
//   var month = now.getMonth()+1
//   var day = now.getDate()
//   var second = now.getSeconds()
//   if (month < 10)
//     month = '0'+month
//   if (day < 10)
//     day = '0'+day
//   return year+'-'+month+'-'+day+'-'+second
// }
Page({
  data: {
    filePath:'',
    cloudPath:'',
    photoUrl:''
  },
  onLoad: function(options) {
    // console.log(formatDate())
  },
  selectImage:function(){
    wx.chooseImage({
      count: 1,
      sizeType: ['original', 'compressed'],
      sourceType: ['album', 'camera'],
      success: res => {
        wx.showToast({
          title:"上传中"
        })
        const filePath = res.tempFilePaths[0]
        // console.log(filePath)
        // // 自定义云端的图片名称
        let cloudPath = 
        Math.floor(Math.random() * 1000000)
         + filePath.match(/\.[^.]+?$/)[0];
         wx.cloud.uploadFile({
           filePath,
           cloudPath,
           success: res => {
             wx.showToast({
               title: '上传Ok',
               duration:3000
             });
             console.log(res.fileID)
             this.setData({
               photoUrl:res.fileID
             })
           },
           fail: () => {},
           complete: () => {}
         });
           
          
      },
      fail: () => {},
      complete: () => {}
    });
    console.log("hi")
    console.log(this.data.photoUrl)
  },
  generateId:function(){
    return Number(Math.random().toString().substr(3,5) + Date.now()).toString(36);
  },
  submitForm:function(e){
    let info = app.globalData.info
    // let today = formatDate()
    let formData = e.detail.value
    // console.log(this.data.photoUrl)
    var reqTask = wx.request({
      url: baseUrl,
      data: {
        "tid":this.generateId(),
        // "addDay":"",
        "title":formData.title,
        "content":formData.content,
        "discussNum":0,
        "nickName":info.nickName,
        "img":this.data.photoUrl,
        "hotLevel":1,
        "openid": app.globalData.openid
      },
      header: {'content-type':'application/json'},
      method: 'POST',
      dataType: 'json',
      responseType: 'text',
      success: (result)=>{
        console.log("成功")
      }
    });
    // let todayDetail = new Date()
    // subject.add({
    //   data:{
        // title:formData.title,
        // content:formData.content,
        // addDay:today,
        // commentNum:1,
        // avatarUrl:info.avatarUrl,
        // nickName:info.nickName,
        // img:this.data.photoUrl,
        // dayDetail:todayDetail,
        // discuss:0
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
    wx.hideToast(); 
    setTimeout(function () {
      wx.switchTab({
        url: '../../pages/hot/hot',
      });    //要延时执行的代码
     }, 1000) //延迟时间 这里是1秒
  }
});