import { reactive } from 'vue';

export const useModal = reactive({
  modal2Visible: false,
  modalType: null,
});

export function openModal(type) {
  useModal.modalType = type;
  useModal.modal2Visible = true;
}

export function closeModal() {
  useModal.modal2Visible = false;
  useModal.modalType = null;
}
