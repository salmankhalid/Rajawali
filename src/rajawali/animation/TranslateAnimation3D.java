package rajawali.animation;

import rajawali.ITransformable3D;
import rajawali.math.Number3D;

public class TranslateAnimation3D extends Animation3D {
	protected Number3D mToPosition;
	protected Number3D mFromPosition;
	protected Number3D mDiffPosition;
	protected Number3D mMultipliedPosition = new Number3D();
	protected Number3D mAddedPosition = new Number3D();
	
	public TranslateAnimation3D(Number3D toPosition) {
		super();
		mToPosition = toPosition;
	}
	
	public TranslateAnimation3D(Number3D fromPosition, Number3D toPosition)
	{
		super();
		mFromPosition = fromPosition;
		mToPosition = toPosition;
	}
	
	@Override
	public void setTransformable3D(ITransformable3D transformable3D) {
		super.setTransformable3D(transformable3D);
		if(mFromPosition == null)
			mFromPosition = new Number3D(transformable3D.getPosition());
	}
	
	@Override
	protected void applyTransformation(float interpolatedTime) {
		if(mDiffPosition == null)
			mDiffPosition = Number3D.subtract(mToPosition, mFromPosition);
		mMultipliedPosition.setAllFrom(mDiffPosition);
		mMultipliedPosition.multiply(interpolatedTime);
		mAddedPosition.setAllFrom(mFromPosition);
		mAddedPosition.add(mMultipliedPosition);
		
		mTransformable3D.getPosition().setAllFrom(mAddedPosition);
	}
}
