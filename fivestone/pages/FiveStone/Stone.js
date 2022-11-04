// 棋子的实体类
module.exports={
    none:0,//无棋子
    black:1,//黑棋
    white:2,//白棋
    toString: function(i){
        switch(i){
            case this.black: return 'black';
            case this.white: return 'white';
            default: return 'none';
        }
    }
}