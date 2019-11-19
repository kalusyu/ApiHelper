package com.sz.ucar.lib.demo.customview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.sz.ucar.lib.demo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author biaowen.yu
 * @date created at : 2019/10/15
 **/
public class UNodeProgressbar extends View {

    // 节点数量
    private int nodesNum;
    // 进行中的图标
    private Drawable progressingDrawable;
    private Drawable unprogressingDrawable;
    // 失败的节点
    private Drawable progresFailDrawable;
    // 成功的节点
    private Drawable progresSuccDrawable;

    // 节点的半径
    private int nodeRadius;
    // 进度条的颜色
    private int processingLineColor;

    // 当前进行到的节点编号。从0开始计算
    private int currNodeNO;

    // 当前进行到的节点编号所对应的状态 0：失败  1：成功
    private int currNodeState;
    Context mContext;

    int mWidth, mHeight;
    private Paint mPaint;
    private Canvas mCanvas;
    // mCanvas绘制在这上面
    private Bitmap mBitmap;

    private int DEFAULT_LINE_COLOR = Color.BLUE;

    private Paint mTextPaint;

    private List<Node> mNodes = new ArrayList<>();

    public void setNodes(List<Node> nodes){
        mNodes.clear();
        mNodes.addAll(nodes);
    }

    public UNodeProgressbar(Context context) {
        this(context, null);

    }

    public UNodeProgressbar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public UNodeProgressbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;

        TypedArray mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.UNodeProgressbar);
        // 默认一个节点
        nodesNum = mTypedArray.getInteger(R.styleable.UNodeProgressbar_nodesNum, 1);
        // 节点半径
        nodeRadius = mTypedArray.getDimensionPixelSize(R.styleable.UNodeProgressbar_nodeRadius, 10);
        progressingDrawable = mTypedArray.getDrawable(R.styleable.UNodeProgressbar_progressingDrawable);
        unprogressingDrawable = mTypedArray.getDrawable(R.styleable.UNodeProgressbar_unprogressingDrawable);
        progresFailDrawable = mTypedArray.getDrawable(R.styleable.UNodeProgressbar_progresFailDrawable);
        progresSuccDrawable = mTypedArray.getDrawable(R.styleable.UNodeProgressbar_progresSuccDrawable);
        processingLineColor = mTypedArray.getColor(R.styleable.UNodeProgressbar_processingLineColor, DEFAULT_LINE_COLOR);
        currNodeState = mTypedArray.getInt(R.styleable.UNodeProgressbar_currNodeState, 1);
        currNodeNO = mTypedArray.getInt(R.styleable.UNodeProgressbar_currNodeNO, 1);

        mPaint = new Paint();
        mPaint.setColor(processingLineColor);
        mPaint.setAntiAlias(true);
        // 圆角
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        // 圆角
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        Path path = new Path();
        path.addCircle(10, 10, 10, Path.Direction.CW);
        mPaint.setPathEffect(new PathDashPathEffect(path, 30, 0, PathDashPathEffect.Style.TRANSLATE));


        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setColor(Color.BLUE);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
        mBitmap = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);

        float nodeWidth = ((float) mWidth) / (nodesNum - 1);
        for (int i = 0; i < nodesNum; i++) {
            Node node = new Node();
            if (i == 0) {
                node.mPoint = new Point(((int) nodeWidth * i), mHeight / 2 - nodeRadius);
            } else if (i == (nodesNum - 1)) {
                node.mPoint = new Point(((int) nodeWidth * i) - nodeRadius * 2, mHeight / 2 - nodeRadius);
            } else {
                node.mPoint = new Point(((int) nodeWidth * i) - nodeRadius, mHeight / 2 - nodeRadius);
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawProgress();
        if (mBitmap != null) {
            canvas.drawBitmap(mBitmap, new Rect(0, 0, mBitmap.getWidth(), mBitmap.getHeight()),
                    new Rect(0, 0, mBitmap.getWidth(), mBitmap.getHeight()), mPaint);
        }

        for (int i = 0; i < mNodes.size(); i++) {
            Node node = mNodes.get(i);
            //已完成的进度节点
            if (node.type == Node.FINISHING) {

                progressingDrawable.setBounds(node.mPoint.x, node.mPoint.y, node.mPoint.x + nodeRadius * 2, node.mPoint.y + nodeRadius * 2);
                progressingDrawable.draw(canvas);

                //当前所到达的进度节点（终点）
            } else if (i == Node.FINISHING) {
                //判断是成功还是失败  0 :失败  1：成功
                if (node.type == Node.FINISHING) {

                    if (progresSuccDrawable != null) {
                        progresSuccDrawable.setBounds(node.mPoint.x, node.mPoint.y,
                                node.mPoint.x + nodeRadius * 2, node.mPoint.y + nodeRadius * 2);
                        progresSuccDrawable.draw(canvas);
                    }
                } else {
                    if (progresFailDrawable != null) {
                        progresFailDrawable.setBounds(node.mPoint.x, node.mPoint.y,
                                node.mPoint.x + nodeRadius * 2, node.mPoint.y + nodeRadius * 2);
                        progresFailDrawable.draw(canvas);
                    }
                }
            } else {  //未完成的进度节点

                unprogressingDrawable.setBounds(node.mPoint.x, node.mPoint.y,
                        node.mPoint.x + nodeRadius * 2, node.mPoint.y + nodeRadius * 2);
                unprogressingDrawable.draw(canvas);
            }
        }
    }

    private void drawProgress() {
        //先画背景
        Paint bgPaint = new Paint();
        bgPaint.setColor(Color.parseColor("#f0f0f0"));
        mCanvas.drawRect(0, 0, mWidth, mHeight, bgPaint);
        //先画线段，线段的高度为nodeRadius/2
        mPaint.setStrokeWidth(nodeRadius / 2);
        //前半截线段
//        mCanvas.drawLine(nodeRadius, mHeight/2, mWidth-nodeRadius, mHeight/2, mPaint);  //线段2端去掉nodeRadius
        //线段2端去掉nodeRadius
        mCanvas.drawLine(nodeRadius * 2, mHeight / 2 - 10, mNodes.get(1).mPoint.x + nodeRadius,
                mNodes.get(1).mPoint.y + nodeRadius, mPaint);
        //后半截线段
        //线段2端去掉nodeRadius
        mPaint.setColor(Color.parseColor("#dddddd"));
        mCanvas.drawLine(mNodes.get(1).mPoint.x + nodeRadius, mNodes.get(1).mPoint.y + nodeRadius,
                mWidth - nodeRadius, mHeight / 2, mPaint);
    }



    public class Node {

        public static final int FINISHING = 1;
        public static final int UNFINISH = 0;

        private Point mPoint;
        private int type; //0:已完成  1:当前到达的进度节点
        private String text;
        private String time;

        public Point getmPoint() {
            return mPoint;
        }

        public void setmPoint(Point mPoint) {
            this.mPoint = mPoint;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
