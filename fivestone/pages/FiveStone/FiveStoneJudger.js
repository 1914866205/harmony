// 五子棋结束判定
var Stone=require('Stone');

/**
 * 判断该棋盘上的子是否符合
 */

function judge(stepStone,x,y,searchUp,judgeDirectionItem){
    var count=0;
    //向上或者向下找
    var loc=judgeDirectionItem(x,y,searchUp);
    var lastLoc={
        x:x,
        y:y
    };
    //在棋盘内
    while(loc.x<this.chessBoardSize&&loc.y<this.chessBoardSize&&loc.x>=0&&loc.y>=0){
        if(this.chessBoard[loc.x][loc.y].stoneType==stepStone){
            lastLoc.x=loc.x;
            lastLoc.y=loc.y;
            count++;
        }else{
            break;
        }
        //递归，继续向里面找
        //??????不太理解，如果继续找，应该换当前点位置了
        loc=judgeDirectionItem(loc.x,loc.y,searchUp);
    }

    //返回本走向的同类棋子的总数以及最远同类棋子的位置
    return {
        count:count,
        'lastLoc':lastLoc
    };
}

/**
 * 这是一个对象
 * 有四个属性 水平、垂直、左斜、右斜
 * 比如传入 1 1 ture 会返回
 * (1,0) (2,1)(0.0)(2,0)
 *   0 1 2 
 * 0 + + +
 * 1 - @ +
 * 2 - - -
 * 比如传入 1 1 false 会返回
  (1,2)(0,1)(2,2)(0,2)
 */
const judgeDirection={
   vertical:(x,y,searchUp)=>{
       return{
           x:x,
           y:searchUp?y-1:y+1
       };
   },
   horizontal:(x,y,searchUp)=>{
       return{
           x:searchUp?x+1:x-1,
           y:y
       };
   },
   /**从上左到右下 */
   leftSkew:(x,y,searchUp)=>{
       return {
           x:searchUp?x-1:x+1,
           y:searchUp?y-1:y+1
       }
   },

   /**从右上到左下 */
   rightSkew:(x,y,searchUp)=>{
       return{
           x:searchUp?x+1:x-1,
           y:searchUp?y-1:y+1
       };
   }
}


module.exports=function(stepStone,x,y,winCallback){
    //遍历四个方向的属性
    //每次只找一个方向的，只要数量>=5则返回结束
    for (var key in judgeDirection){
        // judgeDirectionFunc 是周边的一个棋子位置
        const judgeDirectionItem=judgeDirection[key];
        //在上方找相同点
        const searchUpRes=judge.call(this,stepStone,x,y,true,judgeDirectionItem);
        //在下方找相同点
        const searchDownRes=judge.call(this,stepStone,x,y,false,judgeDirectionItem);
        //向上找到相等的 + 自己
        var count=searchUpRes.count+1;
        //向下找到相等的 加在 一起
        count+=searchDownRes.count;
        if(count>=5){
            //searchUpRes.lastLoc 最上面的位置   
            //searchDownRes.lastLoc 最下面的位置
            winCallback.call(this,stepStone,searchUpRes.lastLoc,searchDownRes.lastLoc)
            return;
        }
        
    }
}