export default {
  /**
   * 站点标题
   */
  title: '风台',
  subtitle: '风吹黑云雨 台上无纤埃',

  /**
   * 关于页面
   */
  aboutOpts: {
    avatar: 'feng.png',
    motto: '长恨此身非我有，何时忘却营营。夜阑风静縠纹平。小舟从此逝，江海寄余生',
    contact:[
      {
        icon: 'clannad1.jpg',
        path: 'https://www.bilibili.com/',
      },
      {
        icon: 'clannad2.jpg',
        path: 'https://github.com/',
      },
      {
        icon: 'clannad3.jpg',
        path: 'https://chanshiyu.com/#/',
      },
    ],
    segment:[
      {
        label: '迪奥·布兰度',
        markdown: '人的能力是有极限的。我从短暂的人生当中学到一件事......越是玩弄计谋,就越会发现人类的能力是有极限的......除非超越人类。',
      },
      {
        label: '迪亚波罗',
        markdown: '这是一场试炼,我认为这就是一场为了战胜过去的试炼。只有战胜了那幼稚的过去，人才能有所成长。',
      },
      {
        label: '恩里克·普奇',
        markdown: '我尊敬第一个吃菇的人…不知道是刚好没吃到毒蕈，或是经验学习而能分辨呢？',
      },
      {
        label: '法尼·瓦伦泰',
        markdown: '吾心吾行澄如明镜，所作所为皆为正义',
      },
    ]
  },

  /**
   * 主题配色，主要用于文章、灵感、关于等卡片配色
   * 推荐一个超棒的取色站，日本の伝統色：http://nipponcolors.com/
   */
  themeColors: [
    '#B28FCE', // 薄
    '#86C166', // 苗
    '#F596AA', // 桃
    '#F19483', // 曙
    '#F9BF45', // 玉子
    '#FAD689', // 浅黄
    '#E79460', // 洗柿
    '#2EA9DF', // 露草
    '#FB966E', // 洗朱
    '#BC9F77', // 白茶
    '#867835', // 黄海松茶
    '#B9887D', // 水がき
  ],
  /**
   * 分类的图标
   */
  categoryIconOptions: [
    {value: 'clannad1.jpg', label: 'clannad1.jpg'},
    {value: 'clannad2.jpg', label: 'clannad2.jpg'},
    {value: 'clannad3.jpg', label: 'clannad3.jpg'},
    {value: 'clannad4.jpg', label: 'clannad4.jpg'},
    {value: 'clannad5.jpg', label: 'clannad5.jpg'},
    {value: 'clannad6.jpg', label: 'clannad6.jpg'},
    {value: 'clannad7.jpg', label: 'clannad7.jpg'},
    {value: 'clannad8.jpg', label: 'clannad8.jpg'},
    {value: 'clannad9.jpg', label: 'clannad9.jpg'},
  ],
  /**
   * 分类的背景图
   */
  categoryBgOptions: [
    {value: 'A_KianaKaslana.jpg', label: 'A_KianaKaslana.jpg'},
    {value: 'B_RaidenMei.jpg', label: 'B_RaidenMei.jpg'},
    {value: 'C_BronyaZaychik.jpg', label: 'C_BronyaZaychik.jpg'},
    {value: 'D_MurataHimeko.jpg', label: 'D_MurataHimeko.jpg'},
    {value: 'E_TheresaApocalypse.jpg', label: 'E_TheresaApocalypse.jpg'},
    {value: 'F_SeeleVollerei.jpg', label: 'F_SeeleVollerei.jpg'},
    {value: 'G_SinMal.jpg', label: 'G_SinMal.jpg'},
    {value: 'H_HouraijiKyuusyou.jpg', label: 'H_HouraijiKyuusyou.jpg'},
    {value: 'I_YssringLeavtruth.jpg', label: 'I_YssringLeavtruth.jpg'},
  ],
  /**
   * 后端接口路径
   */
   controllerPath: 'http://127.0.0.1:81',
   // controllerPath: 'http://192.168.2.128:8181',
   // controllerPath: 'http://8.218.53.237:81',
}