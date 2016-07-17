package com.artifex.mupdf.fitz;

import java.io.IOException;
import java.io.OutputStream;

public class BufferOutputStream extends OutputStream
{
	protected Buffer buffer;
	protected int position;
	protected int resetPosition;

	public BufferOutputStream(Buffer buffer) {
		super();
		this.buffer = buffer;
		this.position = 0;
	}

	public void write(byte[] b) {
		buffer.writeBytes(b);
	}

	public void write(byte[] b, int off, int len) {
		buffer.writeBytesFrom(b, off, len);
	}

	public void write(int b) {
		buffer.writeByte((byte) b);
	}

	public int available() {
		return buffer.getLength();
	}

	public void mark(int readlimit) {
		resetPosition = position;
	}

	public boolean markSupported() {
		return true;
	}

	public int read() {
		return buffer.readByte(position++);
	}

	public int read(byte[] b) {
		int n = buffer.readBytes(position, b);
		position += n;
		return n;
	}

	public int read(byte[] b, int off, int len) {
		int n = buffer.readBytesInto(position, b, off, len);
		position += n;
		return n;
	}

	public void reset() throws IOException {
		if (resetPosition < 0)
			throw  new IOException("cannot reset because mark never set");
		if (resetPosition >= buffer.getLength())
			throw  new IOException("cannot reset because mark set outside of buffer");

		position = resetPosition;
	}
}
