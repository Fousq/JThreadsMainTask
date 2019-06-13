package kz.zhanbolat.jthreads.entity;

import java.util.Objects;

public class Cell {
	private int value;
	private boolean changable = true;
	
	public Cell(int value, boolean changable) {
		this.value = value;
		this.changable = changable;
	}

	public Cell(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public boolean isChangable() {
		return changable;
	}

	public void setChangable(boolean changable) {
		this.changable = changable;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Cell [value=");
		builder.append(value);
		builder.append(", changable=");
		builder.append(changable);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(changable, value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cell other = (Cell) obj;
		return changable == other.changable && value == other.value;
	}
	
}
