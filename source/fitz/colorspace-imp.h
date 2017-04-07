#ifndef MUPDF_FITZ_COLORSPACE_IMP_H
#define MUPDF_FITZ_COLORSPACE_IMP_H

struct fz_colorspace_s
{
	fz_storable storable;
	size_t size;
	char name[16];
	int n;
	int is_subtractive;
	fz_colorspace_convert_fn *to_ccs;
	fz_colorspace_convert_fn *from_ccs;
	fz_colorspace_clamp_fn *clamp;
	fz_colorspace_base_cs_fn *get_base;
	fz_colorspace_destruct_fn *free_data;
	void *data;
};

struct fz_iccprofile_s
{
	int num_devcomp;
	fz_buffer *buffer;
	const char *res_buffer;
	size_t res_size;
	unsigned char md5[16];
	void *cmm_handle;
};

struct fz_icclink_s
{
	fz_storable storable;
	int num_in;
	int num_out;
	int is_identity;
	void *cmm_handle;
};

#endif
